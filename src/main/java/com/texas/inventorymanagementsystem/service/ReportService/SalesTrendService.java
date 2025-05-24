package com.texas.inventorymanagementsystem.service.ReportService;

import com.texas.inventorymanagementsystem.model.SalesOrder;
import com.texas.inventorymanagementsystem.repository.SalesOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalesTrendService {

    @Autowired
    private SalesOrderRepo salesRepo;

    public Map<LocalDateTime, Double> getDailySalesTrend(LocalDateTime startDate, LocalDateTime endDate){
        List<SalesOrder> orders = salesRepo.findByOrderDateBetween(startDate.toLocalDate().atStartOfDay(),
                endDate.toLocalDate().atTime(23,59,59));

        return orders.stream()
                .collect(Collectors.groupingBy(
                        order-> order.getOrderDate(),
                        Collectors.summingDouble( order ->
                                order.getItems().stream()
                                        .mapToDouble(item->
                                                item.getPrice()* item.getQuantity()).sum())

                ));
    }

}
