package com.myapp.cruddemo.dto;
import com.myapp.cruddemo.entity.Product;
import com.myapp.cruddemo.entity.Category;

public class ProductResponseDto {

    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Category category;
    //private int categoryId;
    //private String categoryName;
    public ProductResponseDto(){
    }

    public ProductResponseDto(int id,String name,String description, double price, int stock, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public static ProductResponseDto toDto(Product product){
            return new ProductResponseDto(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getCategory() );
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductRequestDto [name=" + name + ", price=" + price + ", stock=" + stock + ", category=" + category
                + "]";
    }

    
}
