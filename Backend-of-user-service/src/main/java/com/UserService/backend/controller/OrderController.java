package com.UserService.backend.controller;

import com.ProductService.backend.dto.PurchaseRequestDto;
import com.ProductService.backend.dto.PurchaseResponseDto;
import com.UserService.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;


    @PostMapping("/product")
    public ResponseEntity<PurchaseResponseDto> orderProduct(@RequestBody PurchaseRequestDto purchaseRequestDto) {
        PurchaseResponseDto purchaseResponseDto=orderService.orderProduct(purchaseRequestDto);
        return new ResponseEntity<>(purchaseResponseDto, HttpStatus.ACCEPTED);
    }


}
