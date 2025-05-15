package com.texas.inventorymanagementsystem.service.ReportService;

import com.texas.inventorymanagementsystem.DTO.ReportDTO.SalesReport;
import com.texas.inventorymanagementsystem.model.Product;
import com.texas.inventorymanagementsystem.model.SalesOrder;
import com.texas.inventorymanagementsystem.model.SalesOrderItem;
import com.texas.inventorymanagementsystem.repository.SalesOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesReportService {

    @Autowired
    private SalesOrderRepo salesOrderRepo;

    public List<SalesReport> generateSalesReport(LocalDateTime startDate, LocalDateTime endDate) {

        List<SalesOrder> orders = salesOrderRepo.findByOrderDateBetween(startDate, endDate);

        Map<Product, SalesReport> reportMap = new HashMap<>();

        for(SalesOrder order : orders) {
            for(SalesOrderItem item: order.getItems()) {
                Product product = item.getProduct();
                reportMap.computeIfAbsent(product, k -> new SalesReport());

                SalesReport report = reportMap.get(product);
                report.setProductName(product.getName());
                report.setPeriod(startDate);
                report.setQuantitySold(report.getQuantitySold() + item.getQuantity());

                report.setTotalRevenue(report.getTotalRevenue()+ (item.getPrice()*item.getQuantity()));

                double profitPerItem = item.getPrice() - product.getPrice();
                report.setProfit(report.getProfit() + (profitPerItem*item.getQuantity()));
            }
        }

        return new ArrayList<>(reportMap.values());
    }
}
