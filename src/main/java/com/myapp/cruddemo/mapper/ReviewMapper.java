package com.myapp.cruddemo.mapper;

import com.myapp.cruddemo.dto.ReviewResponseDto;
import com.myapp.cruddemo.entity.Review;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    ReviewResponseDto entityToResponseDto(Review review);
}