package com.texas.inventorymanagementsystem.serviceimpl;

import com.texas.inventorymanagementsystem.model.SalesOrder;
import com.texas.inventorymanagementsystem.repository.SalesOrderRepo;
import com.texas.inventorymanagementsystem.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    @Autowired
    private SalesOrderRepo salesOrderRepo;

    @Override
    public SalesOrder addSalesOrder(SalesOrder salesOrder) {
        if(salesOrder.getOrderDate()==null){
            salesOrder.setOrderDate(LocalDateTime.now());
        }
        return salesOrderRepo.save(salesOrder);
    }

    @Override
    public SalesOrder updateSalesOrder(Long id, SalesOrder salesOrder) {

        SalesOrder existingSalesOrder = salesOrderRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "SalesOrder with id "+ id+ "not found"));

        existingSalesOrder.setOrderDate(salesOrder.getOrderDate());

        if (existingSalesOrder.getStatus() != null){
            existingSalesOrder.setStatus(salesOrder.getStatus());
        }
        return salesOrderRepo.save(existingSalesOrder);
    }

    @Override
    public void deleteSalesOrder(Long id) {
        SalesOrder salesOrder = salesOrderRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "SalesOrder with id "+ id+ "not found"));
        salesOrderRepo.delete(salesOrder);
    }

    @Override
    public SalesOrder getSalesOrder(Long id) {
        return salesOrderRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "SalesOrder with id "+ id+ "not found"));
    }

    @Override
    public List<SalesOrder> getSalesOrders() {
        return salesOrderRepo.findAll();
    }
}
