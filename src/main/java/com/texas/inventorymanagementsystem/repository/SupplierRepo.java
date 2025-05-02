package com.texas.inventorymanagementsystem.repository;

import com.texas.inventorymanagementsystem.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepo extends JpaRepository<Supplier,Long> {
    Optional<Supplier> findSupplierByName(String name);
}
