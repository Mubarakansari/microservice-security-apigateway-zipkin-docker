package com.e_commerce.productService.utils;

import com.e_commerce.productService.enums.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDate {
    ResponseType responseType;
    Object feed;
}
