package dev.agasen.ecomm.framework.adapters.output.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.agasen.ecomm.framework.adapters.output.mysql.data.ReviewData;

public interface ReviewRepository extends JpaRepository<ReviewData, Long> {

}
