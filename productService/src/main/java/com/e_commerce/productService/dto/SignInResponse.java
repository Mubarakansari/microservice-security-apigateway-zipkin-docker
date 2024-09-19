package com.e_commerce.productService.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignInResponse {
    private String userName;
    private String password;
    private String token;
}
