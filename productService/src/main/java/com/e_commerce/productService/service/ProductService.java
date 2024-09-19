package com.e_commerce.productService.service;

import com.e_commerce.productService.dto.PaymentResponse;
import com.e_commerce.productService.dto.PurchaseProductDto;
import com.e_commerce.productService.dto.SignUpRequest;
import com.e_commerce.productService.entity.Product;
import com.e_commerce.productService.utils.ResponseDate;

public interface ProductService {
    ResponseDate signIn(SignUpRequest signUpRequest);

    ResponseDate signUp(SignUpRequest signUpRequest);

    Product create(Product product);

    PaymentResponse purchaseProduct(PurchaseProductDto purchaseProductDto);

    ResponseDate fetchAllPaymentByProduct(Long productId);

}
