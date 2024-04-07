package dev.agasen.ecomm.framework.adapters.output.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.agasen.ecomm.framework.adapters.output.mysql.data.ProductData;

public interface ProductRepository extends JpaRepository<ProductData, Long> {
  
}
