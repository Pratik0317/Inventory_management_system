package com.texas.inventorymanagementsystem.service;

import com.texas.inventorymanagementsystem.model.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier addSupplier(Supplier supplier);
    Supplier findSupplierById(Long id);
    Supplier updateSupplier(Long id,Supplier supplier);
    void deleteSupplier(Long id);
    List<Supplier> findAllSuppliers();
}
