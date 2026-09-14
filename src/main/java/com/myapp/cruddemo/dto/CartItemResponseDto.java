package com.myapp.cruddemo.dto;

public class CartItemResponseDto {

    private int id;
    private int quantity;
    private int productId;

    public CartItemResponseDto() {
    }

    public CartItemResponseDto(int id, int quantity, int productId) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}