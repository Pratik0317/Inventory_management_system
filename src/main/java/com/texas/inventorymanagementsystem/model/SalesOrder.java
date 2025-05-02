package com.texas.inventorymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;
    private String status; // e.g., PENDING, SHIPPED, DELIVERED
    @OneToMany(mappedBy = "salesOrder")
    private List<SalesOrderItem> items;
}