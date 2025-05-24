package com.texas.inventorymanagementsystem.service.ReportService;

import com.texas.inventorymanagementsystem.DTO.ReportDTO.InventoryStatusReport;
import com.texas.inventorymanagementsystem.model.Notification;
import com.texas.inventorymanagementsystem.model.Product;
import com.texas.inventorymanagementsystem.repository.NotificationRepo;
import com.texas.inventorymanagementsystem.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertReportService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private NotificationRepo noticeRepo;

    public List<InventoryStatusReport> generateLowStockReport(){
        return productRepo.findAll().stream()
                .filter(p-> p.getQuantity()<10)
                .map(product ->{
                    InventoryStatusReport report = new InventoryStatusReport();
                    report.setProductName(product.getName());
                    report.setSku(product.getSku());
                    report.setCurrentStock(product.getQuantity());
                    report.setStatus("Low Stock");
                    return report;
                })
                .collect(Collectors.toList());
    }
    @Scheduled(cron ="0 0 8 * * SUN-FRI")
    public void checkAndCreateLowStockNotifications(){
        List<Product> lowStockProducts = productRepo.findByQuantityLessThan(10);

        for(Product product : lowStockProducts){
            Notification notification = new Notification();
            notification.setMessage("Low stock alert for product " + product.getName()+
                    ". Current stock: " + product.getQuantity());
            notification.setCreatedAt(LocalDateTime.now());
            notification.setRead(false);

            noticeRepo.save(notification);
        }
    }
}
