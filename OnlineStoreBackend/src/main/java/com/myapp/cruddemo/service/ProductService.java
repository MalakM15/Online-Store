package com.myapp.cruddemo.service;

import com.myapp.cruddemo.dao.ProductRepository;
import com.myapp.cruddemo.dao.CategoryRepository;
import com.myapp.cruddemo.entity.Product;
import com.myapp.cruddemo.entity.Category;

import com.myapp.cruddemo.dto.ProductResponseDto;
import com.myapp.cruddemo.dto.ProductRequestDto;
import com.myapp.cruddemo.mapper.ProductMapper;
import com.myapp.cruddemo.exception.*;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll( pageable)   .map(productMapper::entityToResponseDto);
    }
        
        
    public ProductResponseDto getProduct(int id) {

        Product product = productRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Product not found with id: " + id));
        return  productMapper.entityToResponseDto(product);    
    }

    public List<ProductResponseDto> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name).stream()
        .map(productMapper::entityToResponseDto).toList();
    }

    public List<ProductResponseDto> getProductsByCategory(int categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(productMapper::entityToResponseDto)
                .toList();
    }

    public ProductResponseDto createProduct(ProductRequestDto dto) {
        Product product = productMapper.dtoToEntity(dto);
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(
                    () -> new ResourceNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return productMapper.entityToResponseDto(savedProduct);
    }
    public ProductResponseDto updateProduct(int id, ProductRequestDto dto) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException(
                        "Product not found with id: " + id));

        existingProduct.setName(dto.getName());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setStock(dto.getStock());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(
                    () -> new ResourceNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));

        existingProduct.setCategory(category);
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.entityToResponseDto(updatedProduct);
    }
/*
    public Product updateProduct(int id, Product updatedProduct) {

        Product existingProduct = getProduct(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setCategory(updatedProduct.getCategory());

        return productRepository.save(existingProduct);
    }
        */

    public void deleteProduct(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id));

        productRepository.delete(product);
    }
}