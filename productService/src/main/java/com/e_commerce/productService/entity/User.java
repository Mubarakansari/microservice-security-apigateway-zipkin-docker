package com.e_commerce.productService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "userName name should not blank")
    @Size(min = 5, max = 50)
    @Column(unique = true)
    private String userName;

    @NotBlank
    @JsonIgnore
    @Size(min = 5, max = 100)
    private String password;

}
