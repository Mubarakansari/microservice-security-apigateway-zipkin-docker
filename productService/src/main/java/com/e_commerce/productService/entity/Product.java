package com.e_commerce.productService.entity;

import com.e_commerce.productService.enums.ProductCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "product name should not blank")
    @Size(min = 5, max = 50)
    @Column(unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @NotNull
    private double size;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", productCategory=" + productCategory +
                ", size=" + size +
                '}';
    }
}
