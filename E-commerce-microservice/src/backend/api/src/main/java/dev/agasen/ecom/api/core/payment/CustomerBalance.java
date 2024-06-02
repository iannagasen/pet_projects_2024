package dev.agasen.ecom.api.core.payment;

import lombok.Builder;

@Builder
public record CustomerBalance(String id, Long userId, String customerName, Long balance) { }
