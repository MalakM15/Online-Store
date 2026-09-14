package com.myapp.cruddemo.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderResponseDto {

    private int id;
    private LocalDate orderDate;
    private double totalPrice;
    private int userId;
    private List<OrderItemResponseDto> orderItems;

    public OrderResponseDto() {
    }

    public OrderResponseDto(int id, LocalDate orderDate, double totalPrice,
                            int userId, List<OrderItemResponseDto> orderItems) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItemResponseDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponseDto> orderItems) {
        this.orderItems = orderItems;
    }
}