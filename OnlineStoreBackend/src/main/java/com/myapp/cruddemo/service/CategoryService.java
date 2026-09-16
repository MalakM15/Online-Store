package com.myapp.cruddemo.service;

import com.myapp.cruddemo.dao.CategoryRepository;
import com.myapp.cruddemo.dto.CategoryResponseDto;
import com.myapp.cruddemo.dto.CategoryRequestDto;
import com.myapp.cruddemo.entity.Category;
import com.myapp.cruddemo.exception.*;
import com.myapp.cruddemo.mapper.CategoryMapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryCacheService categoryCacheService;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryCacheService categoryCacheService, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryCacheService = categoryCacheService;
        this.categoryMapper = categoryMapper;

    }

    public List<CategoryResponseDto> getAllCategories() {

        return categoryRepository.findAll().stream()
                .map(categoryMapper::entityToResponseDto)
                .toList();
    }

    public CategoryResponseDto getCategory(int id) {

        Category category = getCategoryEntity(id);
        return categoryMapper.entityToResponseDto(category);
    }
    //@Cacheable(value = "categories", key="#id")
    private Category getCategoryEntity(int id) {

        //System.out.println("  DATABASE: Loading category " + id);

        return categoryCacheService.findCategory(id).orElseThrow(
            () -> new ResourceNotFoundException("Category not found with id: " + id)
        );
    }

    public CategoryResponseDto  createCategory(CategoryRequestDto dto) {
        Category category = categoryMapper.dtoToEntity(dto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.entityToResponseDto(savedCategory);
    }

    public CategoryResponseDto updateCategory(int id, CategoryRequestDto updatedCategory) {

        Category existingCategory = getCategoryEntity(id);

        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());

        Category savedCategory = updateCategoryEntity(existingCategory);

        return categoryMapper.entityToResponseDto(savedCategory);
    }

    @CachePut(value = "categories", key = "#category.id")
    public Category updateCategoryEntity(Category category) {
        return categoryRepository.save(category);
    }

    @CacheEvict(value= "categories", key= "#id")
    public void deleteCategory(int id) {

        Category category = getCategoryEntity(id);
        if (category.getProducts() != null && !category.getProducts().isEmpty() ){
                            throw new BadRequestException("Cannot delete category( "+category.getName()+") it has products.");

        }
        categoryRepository.delete(category);
    }
}