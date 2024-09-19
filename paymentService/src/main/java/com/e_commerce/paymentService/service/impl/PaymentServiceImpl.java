package com.e_commerce.paymentService.service.impl;

import com.e_commerce.paymentService.entity.Payment;
import com.e_commerce.paymentService.exception.GenericException;
import com.e_commerce.paymentService.repository.PaymentRepository;
import com.e_commerce.paymentService.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment create(Payment payment) {
        if (paymentRepository.existsByName(payment.getName()))
            throw new GenericException("Payment product not found...");
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> fetchAllPaymentByProductId(Long productId) {
        if (productId != 1) {
            throw new GenericException("Product id not valid.");
        }
        return paymentRepository.findAllByProductId(productId);
    }
}
