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
import java.util.stream.Collectors;

@Service
public class SalesReportService {

    @Autowired
    private SalesOrderRepo salesOrderRepository;

    public List<SalesReport> generateSalesReport(LocalDateTime startDate, LocalDateTime endDate) {

        LocalDateTime effectiveFromDate = (startDate != null) ? startDate : LocalDateTime.of(2021, 1, 1, 0, 0);
        LocalDateTime effectiveToDate = (endDate != null) ? endDate : LocalDateTime.now();

        List<SalesOrder> orders = salesOrderRepository.findByOrderDateBetween(effectiveFromDate, effectiveToDate);

        Map<String, SalesReport> reportMap = new HashMap<>();

        for (SalesOrder order : orders) {
            if (order.getItems() != null) {  // Null check for items
                for (SalesOrderItem item : order.getItems()) {
                    if (item.getProduct() != null) {  // Null check for product
                        String productName = item.getProduct().getName();

                        // Use productName as key since we can't use Product entity directly
                        reportMap.computeIfAbsent(productName,
                                k -> {
                                    SalesReport newReport = new SalesReport();
                                    newReport.setProductName(productName);
                                    newReport.setPeriod(order.getOrderDate()); // Using actual order date
                                    return newReport;
                                });

                        SalesReport report = reportMap.get(productName);
                        report.setQuantitySold(report.getQuantitySold() + item.getQuantity());
                        report.setTotalRevenue(report.getTotalRevenue() + (item.getPrice() * item.getQuantity()));

                        // Calculate profit (assuming purchase price is stored in Product)
                        double profitPerItem = item.getPrice() - item.getProduct().getPrice();
                        report.setProfit(report.getProfit() + (profitPerItem * item.getQuantity()));
                    }
                }
            }
        }

        return new ArrayList<>(reportMap.values());
    }
}
