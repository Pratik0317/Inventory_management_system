package com.texas.inventorymanagementsystem.service.ReportService;

import com.texas.inventorymanagementsystem.DTO.ReportDTO.InventoryStatusReport;
import com.texas.inventorymanagementsystem.model.Product;
import com.texas.inventorymanagementsystem.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryReportService {

    @Autowired
    private ProductRepo productRepo;

    public List<InventoryStatusReport> generateInventoryStatusReport() {

        List<Product> products = productRepo.findAll();

        return products.stream()
                .map(product -> {
                    InventoryStatusReport report = new InventoryStatusReport();
                    report.setProductName(product.getName());
                    report.setSku(product.getSku());
                    report.setCategory(product.getCategory()!= null ? product.getCategory().getName(): "Uncategorized");
                    report.setCurrentStock(product.getQuantity());
                    report.setPrice(product.getPrice());

                    if(product.getQuantity()==0){
                        report.setStatus("Out of Stock");
                    }else if(product.getQuantity()<10){
                        report.setStatus("Low Stock");
                    }else{
                        report.setStatus("In Stock");
                    }

                    report.setSupplier(product.getSupplier()!=null ? product.getSupplier().getName(): "No Supplier");

                    return report;
                })
                .collect(Collectors.toList());
    }
}
