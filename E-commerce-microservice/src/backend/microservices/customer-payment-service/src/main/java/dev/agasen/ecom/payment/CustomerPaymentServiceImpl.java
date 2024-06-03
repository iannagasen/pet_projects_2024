package dev.agasen.ecom.payment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.agasen.ecom.api.core.payment.CustomerPayment;
import dev.agasen.ecom.api.core.payment.CustomerPaymentService;
import dev.agasen.ecom.api.core.payment.PaymentProcessRequest;
import dev.agasen.ecom.api.event.payment.PaymentStatus;
import dev.agasen.ecom.api.exceptions.CustomerNotFoundException;
import dev.agasen.ecom.api.exceptions.InsufficientBalanceException;
import dev.agasen.ecom.api.exceptions.PaymentNotFoundException;
import dev.agasen.ecom.api.exceptions.UndeductedPaymentRefundException;
import dev.agasen.ecom.payment.mapper.CustomerPaymentMapper;
import dev.agasen.ecom.payment.persistence.CustomerBalanceEntity;
import dev.agasen.ecom.payment.persistence.CustomerBalanceRepository;
import dev.agasen.ecom.payment.persistence.CustomerPaymentEntity;
import dev.agasen.ecom.payment.persistence.CustomerPaymentRepository;
import dev.agasen.ecom.util.event.DuplicateEventValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

  private final CustomerPaymentRepository paymentRepo;
  private final CustomerBalanceRepository balanceRepo;
  private final CustomerPaymentMapper paymentMapper;
  private final DuplicateEventValidator duplicateEventValidator;

  private static final Mono<CustomerBalanceEntity> CUSTOMER_NOT_FOUND = Mono.error(new CustomerNotFoundException());
  private static final Mono<CustomerBalanceEntity> INSUFFICIENT_BALANCE = Mono.error(new InsufficientBalanceException());
  private static final Mono<CustomerPaymentEntity> PAYMENT_NOT_FOUND = Mono.error(new PaymentNotFoundException());
  private static final Mono<CustomerPaymentEntity> CANT_REFUND_UNDEDUCTED_PAYMENT = Mono.error(new UndeductedPaymentRefundException());

  @Override
  @Transactional
  public Mono<CustomerPayment> process(PaymentProcessRequest request) {
    return duplicateEventValidator.validate(
        this.paymentRepo.existsByOrderId(request.orderId()),
        this.balanceRepo.findByCustomerId(request.customerId())
    )
        .switchIfEmpty(CUSTOMER_NOT_FOUND)
        .filter(c -> c.getBalance() >= request.amount())
        .switchIfEmpty(INSUFFICIENT_BALANCE)
        .flatMap(balance -> doProcessPayment(balance, request))
        .doOnNext(paymentEntity -> log.info("Payment processed for {}", paymentEntity.getOrderId()))
        .map(paymentMapper::toRestModel);
  }

  private Mono<CustomerPaymentEntity> doProcessPayment(CustomerBalanceEntity balance, PaymentProcessRequest paymentRequest) {
    CustomerPaymentEntity payment = paymentMapper.toEntityModel(paymentRequest);
    return balanceRepo.setBalance(balance, balance.getBalance() - payment.getAmount()).then(
           paymentRepo.setPaymentStatus(payment, PaymentStatus.DEDUCTED));
  }

  @Override
  @Transactional
  public Mono<CustomerPayment> refund(Long orderId) {
    return paymentRepo.findByOrderId(orderId)
        .switchIfEmpty(PAYMENT_NOT_FOUND)
        .filter(payment -> PaymentStatus.DEDUCTED.equals(payment.getStatus()))
        .switchIfEmpty(CANT_REFUND_UNDEDUCTED_PAYMENT)
        .zipWhen(payment -> balanceRepo.findByCustomerId(payment.getCustomerId()))
        .flatMap(tuple -> doRefund(tuple.getT2(), tuple.getT1()))
        .doOnNext(paymentEntity -> log.info("Payment processed for {}", paymentEntity.getOrderId()))
        .map(paymentMapper::toRestModel);
  }

  private Mono<CustomerPaymentEntity> doRefund(CustomerBalanceEntity balance, CustomerPaymentEntity payment) {
    return balanceRepo.setBalance(balance, balance.getBalance() + payment.getAmount()).then(
           paymentRepo.setPaymentStatus(payment, PaymentStatus.REFUNDED));
  }


  
}
