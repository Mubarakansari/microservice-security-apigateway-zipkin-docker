package com.e_commerce.productService.dto;

import com.e_commerce.productService.enums.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PurchaseProductDto {
    @NotNull
    private Long productId;
    @NotBlank
    private String name;
    @NotNull
    private PaymentType paymentType;
}
