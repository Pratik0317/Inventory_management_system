package com.texas.inventorymanagementsystem.service;

import com.texas.inventorymanagementsystem.model.SalesOrder;

import java.util.List;

public interface SalesOrderService {
    SalesOrder addSalesOrder(SalesOrder salesOrder);
    SalesOrder updateSalesOrder(Long id, SalesOrder salesOrder);
    void deleteSalesOrder(Long id);
    SalesOrder getSalesOrder(Long id);
    List<SalesOrder> getSalesOrders();
}
