package com.e_commerce.paymentService.controller;

import com.e_commerce.paymentService.entity.Payment;
import com.e_commerce.paymentService.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("paymentController")
public class PaymentController {
    private final PaymentService paymentService;
    private final Environment environment;

    @PostMapping("create")
    public ResponseEntity<Payment> create(@RequestBody @Valid Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(payment));
    }

    @GetMapping("fetchAll/{productId}")
    public ResponseEntity<List<Payment>> fetchAllPaymentByProductId(@PathVariable Long productId) {
        log.info("Port: {}", environment.getProperty("server.port"));
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.fetchAllPaymentByProductId(productId));
    }
}

