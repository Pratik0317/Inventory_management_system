package com.texas.inventorymanagementsystem.repository;

import com.texas.inventorymanagementsystem.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {
}
