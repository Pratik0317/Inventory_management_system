package com.texas.inventorymanagementsystem.serviceimpl;

import com.texas.inventorymanagementsystem.model.PurchaseOrder;
import com.texas.inventorymanagementsystem.model.PurchaseOrderItem;
import com.texas.inventorymanagementsystem.repository.PurchaseOrderRepo;
import com.texas.inventorymanagementsystem.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

    @Override
    public PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder) {
        if (purchaseOrder.getOrderDate() == null) {
            purchaseOrder.setOrderDate(LocalDateTime.now());
        }
        return purchaseOrderRepo.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder) {

        PurchaseOrder existingPurchaseOrder = purchaseOrderRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Purchase Order with id "+id+"not found"));
        existingPurchaseOrder.setOrderDate(purchaseOrder.getOrderDate());
        if (purchaseOrder.getStatus() != null) {
            existingPurchaseOrder.setStatus(purchaseOrder.getStatus());
        }

        return purchaseOrderRepo.save(purchaseOrder);
    }

    @Override
    public void deletePurchaseOrder(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Purchase Order with id "+id+"not found"));
        purchaseOrderRepo.delete(purchaseOrder);
    }

    @Override
    public PurchaseOrder getPurchaseOrder(Long id) {
       return purchaseOrderRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
               "Purchase Order with id "+id+"not found"));
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepo.findAll();
    }
}
