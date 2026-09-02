package com.myapp.cruddemo.dto;
import com.myapp.cruddemo.entity.Product;

public class ProductRequestDto {

    private String name;
    //private String description;
    private double price;
    private int stock;
    private int categoryId;


    public ProductRequestDto(){
    }

    public ProductRequestDto(String name, double price, int stock, int categoryId) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    public static Product fromDto(ProductRequestDto dto){
        Product product = new Product();

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());


        return product;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductRequestDto [name=" + name + ", price=" + price + ", stock=" + stock
                + "]";
    }
    
}
