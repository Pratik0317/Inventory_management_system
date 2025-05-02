package com.texas.inventorymanagementsystem.serviceimpl;

import com.texas.inventorymanagementsystem.model.Supplier;
import com.texas.inventorymanagementsystem.repository.SupplierRepo;
import com.texas.inventorymanagementsystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    @Override
    public Supplier addSupplier(Supplier supplier) {

        if(supplierRepo.findSupplierByName(supplier.getName()).isPresent()){
            throw new RuntimeException("Supplier already exists");
        }
        return supplierRepo.save(supplier);
    }

    @Override
    public Supplier findSupplierById(Long id) {
        return supplierRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found"));
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier supplier) {

        Supplier supplierToUpdate = supplierRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supllier not found"));
        supplierToUpdate.setName(supplier.getName());
        supplierToUpdate.setAddress(supplier.getAddress());
        supplierToUpdate.setContactEmail(supplier.getContactEmail());
        supplierToUpdate.setPhoneNumber(supplier.getPhoneNumber());
        return supplierRepo.save(supplierToUpdate);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found with id: "+id));
        supplierRepo.delete(supplier);
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        return supplierRepo.findAll();
    }
}
