package com.texas.inventorymanagementsystem.service;

import com.texas.inventorymanagementsystem.model.SalesOrderItem;

import java.util.List;

public interface SalesOrderItemService {
    SalesOrderItem addSalesOrderItem(SalesOrderItem salesOrderItem);
    SalesOrderItem updateSalesOrderItem(Long id, SalesOrderItem salesOrderItem);
    void deleteSalesOrderItem(Long id);
    SalesOrderItem getSalesOrderItem(Long id);
    List<SalesOrderItem> getAllSalesOrderItems();
}
