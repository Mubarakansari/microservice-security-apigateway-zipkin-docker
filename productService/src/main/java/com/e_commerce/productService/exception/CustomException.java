package com.e_commerce.productService.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public final class CustomException {
    public static ExceptionResponse customExceptionHandler(FeignException error) {
        String err = error.contentUTF8();
        log.error("CustomException>>" + err);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(err, ExceptionResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, "Internal server error");
    }
}
