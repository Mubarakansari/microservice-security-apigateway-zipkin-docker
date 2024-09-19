package com.e_commerce.paymentService.service;

import com.e_commerce.paymentService.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment create(Payment product);
    List<Payment> fetchAllPaymentByProductId(Long productId);
}
