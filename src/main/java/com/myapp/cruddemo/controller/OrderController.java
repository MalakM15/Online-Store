package com.myapp.cruddemo.controller;
import com.myapp.cruddemo.service.OrderService;
import com.myapp.cruddemo.dto.OrderResponseDto;
import com.myapp.cruddemo.mapper.OrderMapper;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper){
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<OrderResponseDto> getOrders(Authentication authentication) {

        return orderService.getOrders(authentication).stream().map(orderMapper::entityToResponseDto)
                .toList();
    }


    @PostMapping
    public OrderResponseDto placeOrder(Authentication authentication) {

        return orderMapper.entityToResponseDto(orderService.placeOrder(authentication));
    }


}
