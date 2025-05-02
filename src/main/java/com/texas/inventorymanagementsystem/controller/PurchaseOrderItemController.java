package com.texas.inventorymanagementsystem.controller;

import com.texas.inventorymanagementsystem.model.PurchaseOrderItem;
import com.texas.inventorymanagementsystem.service.PurchaseOrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchaseOrderItem")
public class PurchaseOrderItemController {

    @Autowired
    private PurchaseOrderItemsService purchaseOrderItemsService;

    @PostMapping
    private PurchaseOrderItem addPurchaseOrderItem(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderItemsService.savePurchaseOrderItem(purchaseOrderItem);
    }

    @GetMapping("/{id}")
    private ResponseEntity<PurchaseOrderItem> getPurchaseOrderItemById(@PathVariable Long id){
        return ResponseEntity.ok(purchaseOrderItemsService.getPurchaseOrderItem(id));
    }

    @GetMapping
    private ResponseEntity<List<PurchaseOrderItem>> getAllPurchaseOrderItems(){
        return ResponseEntity.ok(purchaseOrderItemsService.getPurchaseOrderItems());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deletePurchaseOrderItem(@PathVariable Long id){
        purchaseOrderItemsService.deletePurchaseOrderItem(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<PurchaseOrderItem> updatePurchaseOrderItem(@PathVariable Long id,@Validated @RequestBody PurchaseOrderItem purchaseOrderItem){
        return ResponseEntity.ok(purchaseOrderItemsService.updatePurchaseOrderItem(id,purchaseOrderItem));
    }
}
