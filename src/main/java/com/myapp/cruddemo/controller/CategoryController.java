package com.myapp.cruddemo.controller;

import com.myapp.cruddemo.service.CategoryService;
import com.myapp.cruddemo.dto.CategoryResponseDto;
import com.myapp.cruddemo.dto.CategoryRequestDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET /api/categories
    @GetMapping
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // GET /api/categories/{id}
    @GetMapping("/{id}")
    public CategoryResponseDto getCategory(@PathVariable int id) {
        return categoryService.getCategory(id);
    }

    // POST /api/categories
    // ADMIN only
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory( @RequestBody CategoryRequestDto dto) {
        CategoryResponseDto savedCategory =categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED) .body(savedCategory);   
    }

    // PUT /api/categories/{id}
    // ADMIN only
    @PutMapping("/{id}")
    public CategoryResponseDto  updateCategory(@PathVariable int id, @RequestBody CategoryRequestDto dto) {

        return categoryService.updateCategory(id, dto);
    }

    // DELETE /api/categories/{id}
    // ADMIN only
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {

        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
