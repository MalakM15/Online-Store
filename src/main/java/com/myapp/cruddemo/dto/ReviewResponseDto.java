package com.myapp.cruddemo.dto;

public class ReviewResponseDto {

    private int id;
    private String comment;
    private int userId;
    private int productId;

    public ReviewResponseDto() {
    }

    public ReviewResponseDto(int id, String comment, int userId, int productId) {
        this.id = id;
        this.comment = comment;
        this.userId = userId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}