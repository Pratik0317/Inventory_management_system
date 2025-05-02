package com.texas.inventorymanagementsystem.model;

import jakarta.persistence.*;

@Entity
public class SalesOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double price;
    @ManyToOne
    private Product product;
    @ManyToOne
    private SalesOrder salesOrder;
}