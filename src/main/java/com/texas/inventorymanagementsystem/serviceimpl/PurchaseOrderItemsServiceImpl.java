package com.texas.inventorymanagementsystem.serviceimpl;

import com.texas.inventorymanagementsystem.model.Product;
import com.texas.inventorymanagementsystem.model.PurchaseOrder;
import com.texas.inventorymanagementsystem.model.PurchaseOrderItem;
import com.texas.inventorymanagementsystem.repository.ProductRepo;
import com.texas.inventorymanagementsystem.repository.PurchaseOrderItemRepo;
import com.texas.inventorymanagementsystem.repository.PurchaseOrderRepo;
import com.texas.inventorymanagementsystem.service.PurchaseOrderItemsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PurchaseOrderItemsServiceImpl implements PurchaseOrderItemsService {

    @Autowired
    private PurchaseOrderItemRepo purchaseOrderItemRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

    @Override
    public PurchaseOrderItem savePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        if(purchaseOrderItem.getProduct()!=null &&
        purchaseOrderItem.getProduct().getId()!=null){
            Product product = productRepo.findById(purchaseOrderItem.getProduct().getId()).orElseThrow(
                    ()->
            new EntityNotFoundException("Product with id "+purchaseOrderItem.getProduct().getId()+" not found"));
            purchaseOrderItem.setProduct(product);
        }

        if(purchaseOrderItem.getPurchaseOrder()!=null&& purchaseOrderItem.getPurchaseOrder().getId()!=null){

            PurchaseOrder purchaseOrder = purchaseOrderRepo.findById(purchaseOrderItem.getPurchaseOrder().getId()).orElseThrow(
                    ()-> new EntityNotFoundException("PurchaseOrder with id "+purchaseOrderItem.getPurchaseOrder().getId()+" not found")
            );
            purchaseOrderItem.setPurchaseOrder(purchaseOrder);
        }
        return purchaseOrderItemRepo.save(purchaseOrderItem);
    }

    @Override
    public PurchaseOrderItem getPurchaseOrderItem(Long id) {
        return purchaseOrderItemRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Purchase OrderItem with id "+id+" not found"));
    }

    @Override
    public PurchaseOrderItem updatePurchaseOrderItem(Long id, PurchaseOrderItem purchaseOrderItem) {

        PurchaseOrderItem existingPurchaseOrderItem = purchaseOrderItemRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Purchase OrderItem with id "+id+" not found"));
        existingPurchaseOrderItem.setQuantity(purchaseOrderItem.getQuantity());
        existingPurchaseOrderItem.setPrice(purchaseOrderItem.getPrice());
        existingPurchaseOrderItem.setProduct(purchaseOrderItem.getProduct());
        return purchaseOrderItemRepo.save(existingPurchaseOrderItem);
    }

    @Override
    public void deletePurchaseOrderItem(Long id) {
        PurchaseOrderItem purchaseOrderItem = purchaseOrderItemRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Purchase OrderItem with id "+id+" not found")
        );
    }

    @Override
    public List<PurchaseOrderItem> getPurchaseOrderItems() {
        return purchaseOrderItemRepo.findAll();
    }
}
