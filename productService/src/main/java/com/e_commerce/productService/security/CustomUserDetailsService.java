package com.e_commerce.productService.security;

import com.e_commerce.productService.entity.User;
import com.e_commerce.productService.exception.GenericException;
import com.e_commerce.productService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> credential = userRepository.findByUserName(username);
        return credential.map(user -> new CustomUserDetails(user)).orElseThrow(() -> {
            throw new GenericException("User Name not valid");
        });
    }
}
