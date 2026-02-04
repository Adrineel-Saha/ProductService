package com.cognizant.productservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Product_Id")
    private Long id;

    @Column(name="Product_Name")
    private String name;

    @Column(name="Description")
    private String description;

    @Column(name="Price")
    private double price;

    @Column(name="Stock")
    private int stock;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
