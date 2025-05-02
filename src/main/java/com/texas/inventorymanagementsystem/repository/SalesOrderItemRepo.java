package com.texas.inventorymanagementsystem.repository;

import com.texas.inventorymanagementsystem.model.SalesOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderItemRepo extends JpaRepository<SalesOrderItem,Long> {

}
