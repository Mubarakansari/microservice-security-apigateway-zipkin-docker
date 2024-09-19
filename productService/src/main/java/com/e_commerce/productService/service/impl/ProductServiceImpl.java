package com.e_commerce.productService.service.impl;

import com.e_commerce.productService.dto.PaymentResponse;
import com.e_commerce.productService.dto.PurchaseProductDto;
import com.e_commerce.productService.dto.SignInResponse;
import com.e_commerce.productService.dto.SignUpRequest;
import com.e_commerce.productService.entity.Product;
import com.e_commerce.productService.entity.User;
import com.e_commerce.productService.enums.ResponseType;
import com.e_commerce.productService.exception.CustomException;
import com.e_commerce.productService.exception.GenericException;
import com.e_commerce.productService.repository.ProductRepository;
import com.e_commerce.productService.repository.UserRepository;
import com.e_commerce.productService.security.JwtTokenUtil;
import com.e_commerce.productService.service.ProductService;
import com.e_commerce.productService.utils.PaymentFeignClient;
import com.e_commerce.productService.utils.ResponseDate;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final PaymentFeignClient paymentFeignClient;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseDate signIn(SignUpRequest signUpRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signUpRequest.userName(), signUpRequest.password()));
        if (authenticate.isAuthenticated()) {
            return new ResponseDate(ResponseType.SUCCESS, SignInResponse.builder().userName(signUpRequest.userName()).password(signUpRequest.password()).token(jwtTokenUtil.generateToken(signUpRequest.userName())).build());
        } else {
            throw new GenericException("Username Or Password invalid.");
        }
    }

    @Override
    public ResponseDate signUp(SignUpRequest signUpRequest) {
        userRepository.save(new User(signUpRequest.userName(), passwordEncoder.encode(signUpRequest.password())));
        return new ResponseDate(ResponseType.SUCCESS, "SignUp Successfully");
    }

    @Override
    public Product create(Product product) {
        if (productRepository.existsByName(product.getName())) throw new GenericException("Product already exists.");
        return productRepository.save(product);
    }

    @Override
    public PaymentResponse purchaseProduct(PurchaseProductDto purchaseProductDto) {
        return paymentFeignClient.create(purchaseProductDto);
    }

    @Override
    @CircuitBreaker(name = "paymentService", fallbackMethod = "getAllPaymentDetails")
    public ResponseDate fetchAllPaymentByProduct(Long productId) {
        log.error("fetchAllPaymentByProduct>>" + productId);
        try {
            return new ResponseDate(ResponseType.NONE, paymentFeignClient.fetchAllPaymentByProductId(productId));
        } catch (FeignException error) {
            log.error("roor>>" + error);
            throw new GenericException(CustomException.customExceptionHandler(error).getMessage());
        }
    }

    public ResponseDate getAllPaymentDetails(Long productId, Exception e) {
        log.error("Fallback method invoked due to: {}", e.getMessage());
        return new ResponseDate(ResponseType.ERROR, "Product service down, Please try after some time...");
    }
}
