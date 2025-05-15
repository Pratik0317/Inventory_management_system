package com.texas.inventorymanagementsystem.controller;

import com.texas.inventorymanagementsystem.model.SalesOrderItem;
import com.texas.inventorymanagementsystem.service.ProductService;
import com.texas.inventorymanagementsystem.service.SalesOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesOrderItem")
public class SalesOrderItemController {

    @Autowired
    private SalesOrderItemService salesOrderItemService;

    @Autowired
    private ProductService productService;

    @PostMapping
    private ResponseEntity<SalesOrderItem> addSalesOrderItem(@RequestBody SalesOrderItem salesOrderItem) {
        return ResponseEntity.ok(salesOrderItemService.addSalesOrderItem(salesOrderItem));
    }

    @GetMapping("/{id}")
    private ResponseEntity<SalesOrderItem> getSalesOrderItem(@PathVariable Long id) {
        return ResponseEntity.ok(salesOrderItemService.getSalesOrderItem(id));
    }

    @GetMapping
    private ResponseEntity<List<SalesOrderItem>> findAllSalesOrderItems() {
        return ResponseEntity.ok(salesOrderItemService.getAllSalesOrderItems());
    }

    @PutMapping("/{id}")
    private ResponseEntity<SalesOrderItem> updateSalesOrderItem(@PathVariable Long id, @RequestBody SalesOrderItem salesOrderItem) {
        return ResponseEntity.ok(salesOrderItemService.updateSalesOrderItem(id, salesOrderItem));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<SalesOrderItem> deleteSalesOrderItem(@PathVariable Long id) {
        salesOrderItemService.deleteSalesOrderItem(id);
        return ResponseEntity.ok().build();
    }
}
