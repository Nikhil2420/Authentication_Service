package com.UserService.backend.service;

import com.ProductService.backend.dto.PurchaseRequestDto;
import com.ProductService.backend.dto.PurchaseResponseDto;
import com.UserService.backend.entity.User;
import com.UserService.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    @Value("${product.service.url}")
    private String productServiceUrl;


    public PurchaseResponseDto orderProduct(PurchaseRequestDto purchaseRequestDto) {

        String userName = getUserName();
        User user = userRepository.findByUserName(userName).orElseThrow(() ->
                new RuntimeException("no user found for this userName : " + userName)
        );
        setUserDetails(purchaseRequestDto, user);

        return restTemplate.postForObject(
                productServiceUrl,
                purchaseRequestDto,
                PurchaseResponseDto.class
        );
    }

    public void setUserDetails(PurchaseRequestDto purchaseRequestDto, User user) {
        purchaseRequestDto.setUserId(user.getId());
        purchaseRequestDto.setUserName(user.getUserName());
        purchaseRequestDto.setRole(user.getRole());
    }

    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
