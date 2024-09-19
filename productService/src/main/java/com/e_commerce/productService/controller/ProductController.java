package com.e_commerce.productService.controller;

import com.e_commerce.productService.dto.PaymentResponse;
import com.e_commerce.productService.dto.PurchaseProductDto;
import com.e_commerce.productService.dto.SignUpRequest;
import com.e_commerce.productService.entity.Product;
import com.e_commerce.productService.service.ProductService;
import com.e_commerce.productService.utils.ResponseDate;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("productController")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("sign-in")
    public ResponseEntity<ResponseDate> signIn(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.signIn(signUpRequest));
    }

    @PostMapping("sign-up")
    public ResponseEntity<ResponseDate> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.signUp(signUpRequest));
    }

    @GetMapping("fetch-all-payment/{productId}")
    public ResponseEntity<ResponseDate> fetchAllPaymentByProductId(@PathVariable Long productId) {
        log.info("fetchAllPaymentByProductId Call: {}", productId);
        return ResponseEntity.status(HttpStatus.OK).body(productService.fetchAllPaymentByProduct(productId));
    }

    @PostMapping("create")
    public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
    }

    @PostMapping("purchase-product")
    public ResponseEntity<PaymentResponse> purchaseProduct(@RequestBody @Valid PurchaseProductDto product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.purchaseProduct(product));
    }

}

