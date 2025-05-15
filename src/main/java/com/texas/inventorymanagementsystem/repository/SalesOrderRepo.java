package com.texas.inventorymanagementsystem.repository;

import com.texas.inventorymanagementsystem.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SalesOrderRepo extends JpaRepository<SalesOrder,Long> {
    List<SalesOrder> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
