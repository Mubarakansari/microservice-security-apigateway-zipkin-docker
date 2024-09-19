package com.e_commerce.productService.dto;

import com.e_commerce.productService.enums.PaymentType;

public record PaymentResponse(Long paymentId, String name, PaymentType paymentType, Long productId) {
}
