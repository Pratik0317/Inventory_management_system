package com.texas.inventorymanagementsystem.serviceimpl;

import com.texas.inventorymanagementsystem.model.Product;
import com.texas.inventorymanagementsystem.model.SalesOrder;
import com.texas.inventorymanagementsystem.model.SalesOrderItem;
import com.texas.inventorymanagementsystem.repository.ProductRepo;
import com.texas.inventorymanagementsystem.repository.SalesOrderItemRepo;
import com.texas.inventorymanagementsystem.repository.SalesOrderRepo;
import com.texas.inventorymanagementsystem.service.SalesOrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SalesOrderItemServiceImpl implements SalesOrderItemService {

    @Autowired
    private SalesOrderItemRepo salesOrderItemRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SalesOrderRepo salesOrderRepo;

    @Override
    @Transactional
    public SalesOrderItem addSalesOrderItem(SalesOrderItem salesOrderItem) {
        if(salesOrderItem.getProduct()!=null &&
        salesOrderItem.getProduct().getId()!=null){
            Product product = productRepo.findById(salesOrderItem.getProduct().getId()).orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with id "+salesOrderItem.getProduct().getId()+" not found"));
            salesOrderItem.setProduct(product);
        }

        if(salesOrderItem.getSalesOrder()!=null &&
        salesOrderItem.getSalesOrder().getId()!=null){
            SalesOrder salesOrder = salesOrderRepo.findById(salesOrderItem.getSalesOrder().getId()).orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,"SalesOrder with id "+salesOrderItem.getSalesOrder().getId()+" not found"));
            salesOrderItem.setSalesOrder(salesOrder);
        }
        return salesOrderItemRepo.save(salesOrderItem);
    }

    @Override
    public SalesOrderItem updateSalesOrderItem(Long id, SalesOrderItem salesOrderItem) {

        SalesOrderItem existingItems = salesOrderItemRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"SalesOrderItem with id "+id+" not found"));

        Product product = productRepo.findById(salesOrderItem.getProduct().getId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Product with id "+salesOrderItem.getProduct().getId()+" not found"));

        SalesOrder salesOrder = salesOrderRepo.findById(salesOrderItem.getSalesOrder().getId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales Order with id: "+salesOrderItem.getSalesOrder().getId()+" not found"));

        existingItems.setProduct(product);
        existingItems.setSalesOrder(salesOrder);
        existingItems.setPrice(salesOrderItem.getPrice());
        existingItems.setQuantity(salesOrderItem.getQuantity());

        return salesOrderItemRepo.save(existingItems);
    }

    @Override
    public void deleteSalesOrderItem(Long id) {
        SalesOrderItem salesOrderItem = salesOrderItemRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"SalesOrderItem with id "+id+" not found"));

        salesOrderItemRepo.delete(salesOrderItem);
    }

    @Override
    public SalesOrderItem getSalesOrderItem(Long id) {
        return salesOrderItemRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"SalesOrderItem with id "+id+" not found"));
    }

    @Override
    public List<SalesOrderItem> getAllSalesOrderItems() {
        return salesOrderItemRepo.findAll();
    }
}
