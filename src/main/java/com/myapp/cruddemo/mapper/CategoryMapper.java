package com.myapp.cruddemo.mapper;

import com.myapp.cruddemo.dto.CategoryRequestDto;
import com.myapp.cruddemo.dto.CategoryResponseDto;
import com.myapp.cruddemo.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category dtoToEntity(CategoryRequestDto dto);

    CategoryResponseDto entityToResponseDto(Category category);
}