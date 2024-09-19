package com.e_commerce.productService.utils;

import com.e_commerce.productService.dto.PaymentResponse;
import com.e_commerce.productService.dto.PurchaseProductDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PAYMENT-SERVICE", path ="paymentController")
public interface PaymentFeignClient {
    @PostMapping("create")
    PaymentResponse create(@RequestBody @Valid PurchaseProductDto payment);

    @GetMapping("fetchAll/{productId}")
    List<PaymentResponse> fetchAllPaymentByProductId(@PathVariable Long productId);
}
