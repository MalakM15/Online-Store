package com.myapp.cruddemo.mapper;

import com.myapp.cruddemo.dto.ProductRequestDto;
import com.myapp.cruddemo.dto.ProductResponseDto;
import com.myapp.cruddemo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product dtoToEntity(ProductRequestDto dto);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    ProductResponseDto entityToResponseDto(Product product);
}