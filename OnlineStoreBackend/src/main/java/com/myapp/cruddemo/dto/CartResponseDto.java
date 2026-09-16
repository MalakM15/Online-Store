package com.myapp.cruddemo.dto;

import java.util.List;

public class CartResponseDto {

    private int id;
    private int userId;
    private List<CartItemResponseDto> cartItems;

    public CartResponseDto() {
    }

    public CartResponseDto(int id, int userId, List<CartItemResponseDto> cartItems) {
        this.id = id;
        this.userId = userId;
        this.cartItems = cartItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartItemResponseDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemResponseDto> cartItems) {
        this.cartItems = cartItems;
    }
}