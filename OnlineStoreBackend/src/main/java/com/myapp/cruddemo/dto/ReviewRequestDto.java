package com.myapp.cruddemo.dto;

public class ReviewRequestDto {
    private String comment;

    public ReviewRequestDto() {
    }

    public ReviewRequestDto(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}