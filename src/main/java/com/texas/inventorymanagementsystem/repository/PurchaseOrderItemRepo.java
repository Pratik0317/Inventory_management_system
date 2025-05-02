package com.texas.inventorymanagementsystem.repository;

import com.texas.inventorymanagementsystem.model.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderItemRepo extends JpaRepository<PurchaseOrderItem, Long> {

}
