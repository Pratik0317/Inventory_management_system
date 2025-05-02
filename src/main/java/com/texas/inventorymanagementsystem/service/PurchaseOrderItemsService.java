package com.texas.inventorymanagementsystem.service;

import com.texas.inventorymanagementsystem.model.PurchaseOrderItem;

import java.util.List;

public interface PurchaseOrderItemsService {

    PurchaseOrderItem savePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem);
    PurchaseOrderItem getPurchaseOrderItem(Long id);
    PurchaseOrderItem updatePurchaseOrderItem(Long id, PurchaseOrderItem purchaseOrderItem);
    void deletePurchaseOrderItem(Long id);
    List<PurchaseOrderItem> getPurchaseOrderItems();
}
