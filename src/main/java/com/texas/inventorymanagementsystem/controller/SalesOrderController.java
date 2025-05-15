package com.texas.inventorymanagementsystem.controller;

import com.texas.inventorymanagementsystem.model.SalesOrder;
import com.texas.inventorymanagementsystem.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesOrder")
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesOrderService;

    @PostMapping
    private ResponseEntity<SalesOrder> addSalesOrder(@RequestBody SalesOrder salesOrder){
        return ResponseEntity.ok(salesOrderService.addSalesOrder(salesOrder));
    }

    @GetMapping("/{id}")
    private ResponseEntity<SalesOrder> getSalesOrderById(@PathVariable Long id){
        return  ResponseEntity.ok(salesOrderService.getSalesOrder(id));
    }

    @PutMapping("/{id}")
    private ResponseEntity<SalesOrder> updateSalesOrder(@PathVariable Long id,@Validated @RequestBody SalesOrder salesOrder){
        return ResponseEntity.ok(salesOrderService.updateSalesOrder(id, salesOrder));
    }

    @GetMapping
    private ResponseEntity<List<SalesOrder>> getAllSalesOrders(){
        return ResponseEntity.ok(salesOrderService.getSalesOrders());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteSalesOrders(@PathVariable Long id){
        salesOrderService.deleteSalesOrder(id);
        return ResponseEntity.noContent().build();
    }
}
