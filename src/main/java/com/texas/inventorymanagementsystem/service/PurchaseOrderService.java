package com.texas.inventorymanagementsystem.service;

import com.texas.inventorymanagementsystem.model.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {

    PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder);
    PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder);
    void deletePurchaseOrder(Long id);
    PurchaseOrder getPurchaseOrder(Long id);
    List<PurchaseOrder> getAllPurchaseOrders();
}
