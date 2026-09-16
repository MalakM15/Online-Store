package com.myapp.cruddemo.controller;

import com.myapp.cruddemo.service.ProductService;
import com.myapp.cruddemo.dto.ProductResponseDto;
import com.myapp.cruddemo.dto.ProductRequestDto;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products
    @GetMapping
    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }
    // GET /api/products/search?name=phone
    @GetMapping("/search")
    public List<ProductResponseDto> searchProducts( @RequestParam String name) {
        return productService.searchProducts(name);
    }

    // GET /api/products/{id}
    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductResponseDto> getProductsByCategory(@PathVariable int categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    // POST /api/products
    // ADMIN only
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto product) {

        ProductResponseDto savedProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // PUT /api/products/{id}
    // ADMIN only
    @PutMapping("/{id}")
    public ProductResponseDto updateProduct( @PathVariable int id, @RequestBody ProductRequestDto product) {
        return productService.updateProduct(id, product);
    }

    // DELETE /api/products/{id}
    // ADMIN only
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}