package com.texas.inventorymanagementsystem.service.ReportService;

import com.texas.inventorymanagementsystem.DTO.ReportDTO.CategoryAnalysisReport;
import com.texas.inventorymanagementsystem.model.Category;
import com.texas.inventorymanagementsystem.model.Product;
import com.texas.inventorymanagementsystem.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryReportService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<CategoryAnalysisReport> generateCategoryAnalysisReport() {
        List<Category> categories = categoryRepo.findAll();

        return categories.stream()
                .map(category -> {
                    CategoryAnalysisReport report = new CategoryAnalysisReport();
                    report.setCategoryName(category.getName());

                    List<Product> products = category.getProducts();
                    report.setProductCount(products.size());

                    int totalStock = products.stream()
                            .mapToInt(Product::getQuantity)
                            .sum();

                    report.setTotalStock(totalStock);

                    double totalValue = products.stream()
                            .mapToDouble(p -> p.getPrice()*p.getQuantity())
                            .sum();

                    report.setTotalValue(totalValue);

                    return report;
                })
                .collect(Collectors.toList());
    }
}
