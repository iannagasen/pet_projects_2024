package dev.agasen.ecomm.framework.adapters.output.mysql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.agasen.ecomm.framework.adapters.output.mysql.data.ReviewListData;

public interface ReviewListRepository extends JpaRepository<ReviewListData, Long> {
  Optional<ReviewListData> findByProductId(Long productId);
}
