package com.cognizant.productservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Products")
@Data
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

}
