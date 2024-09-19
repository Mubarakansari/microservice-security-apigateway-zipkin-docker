package com.e_commerce.paymentService.repository;

import com.e_commerce.paymentService.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByName(String name);

    List<Payment> findAllByProductId(Long productId);
}
