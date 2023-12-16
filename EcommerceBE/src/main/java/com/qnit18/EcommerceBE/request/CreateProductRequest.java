package com.qnit18.EcommerceBE.request;

import com.qnit18.EcommerceBE.model.Size;

import java.util.HashSet;
import java.util.Set;

public class CreateProductRequest {
    private String title;

    private String description;

    private int price;

    private int discountedPrice;

    private int discountNow;

    private int quantity;

    private String brand;

    private String color;

    private Set<Size> size = new HashSet<>();

    private String imageUrl;

    private String topLavelCategory;
    private String secondLavelCategory;
    private String thirdLavelCategory;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String title, String description, int price, int discountedPrice, int discountNow, int quantity, String brand, String color, Set<Size> size, String imageUrl, String topLavelCategory, String secondLavelCategory, String thirdLavelCategory) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.discountNow = discountNow;
        this.quantity = quantity;
        this.brand = brand;
        this.color = color;
        this.size = size;
        this.imageUrl = imageUrl;
        this.topLavelCategory = topLavelCategory;
        this.secondLavelCategory = secondLavelCategory;
        this.thirdLavelCategory = thirdLavelCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getDiscountNow() {
        return discountNow;
    }

    public void setDiscountNow(int discountNow) {
        this.discountNow = discountNow;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<Size> getSize() {
        return size;
    }

    public void setSize(Set<Size> size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTopLavelCategory() {
        return topLavelCategory;
    }

    public void setTopLavelCategory(String topLavelCategory) {
        this.topLavelCategory = topLavelCategory;
    }

    public String getSecondLavelCategory() {
        return secondLavelCategory;
    }

    public void setSecondLavelCategory(String secondLavelCategory) {
        this.secondLavelCategory = secondLavelCategory;
    }

    public String getThirdLavelCategory() {
        return thirdLavelCategory;
    }

    public void setThirdLavelCategory(String thirdLavelCategory) {
        this.thirdLavelCategory = thirdLavelCategory;
    }
}