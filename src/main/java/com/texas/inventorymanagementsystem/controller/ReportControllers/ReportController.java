package com.texas.inventorymanagementsystem.controller.ReportControllers;

import com.texas.inventorymanagementsystem.DTO.ReportDTO.CategoryAnalysisReport;
import com.texas.inventorymanagementsystem.DTO.ReportDTO.InventoryStatusReport;
import com.texas.inventorymanagementsystem.DTO.ReportDTO.SalesReport;
import com.texas.inventorymanagementsystem.service.ReportService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private InventoryReportService inventoryReportService;

    @Autowired
    private SalesReportService salesReportService;

    @Autowired
    private CategoryReportService categoryReportService;

    @Autowired
    private ExcelExportService excelExportService;

    @Autowired
    private SalesTrendService salesTrend;

    @Autowired
    private PdfReportService pdfService;

    @GetMapping("/inventory-status")
    public ResponseEntity<List<InventoryStatusReport>> getInventoryStatus() {
        return ResponseEntity.ok(inventoryReportService.generateInventoryStatusReport());
    }

    @GetMapping("/sales")
    public ResponseEntity<List<SalesReport>> getSalesReport(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        List<SalesReport> report = salesReportService.generateSalesReport(start, end);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/category-analysis")
    public ResponseEntity<List<CategoryAnalysisReport>> getCategoryAnalysis() {
        return ResponseEntity.ok(categoryReportService.generateCategoryAnalysisReport());
    }


    @GetMapping(value = "/inventory-status/excel", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<byte[]> exportInventoryToExcel() throws IOException {
        List<InventoryStatusReport> report = inventoryReportService.generateInventoryStatusReport();
        ByteArrayOutputStream outputStream = excelExportService.exportInventoryToExcel(report);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("inventory_report_"+ LocalDate.now()+ ".xlsx")
                .build());

        return  new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/salesTrend")
    public ResponseEntity<Map<LocalDateTime, Double>> getDailySalesTrend(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {

        LocalDateTime defaultStart = LocalDateTime.now().minusDays(30);
        LocalDateTime defaultEnd = LocalDateTime.now();

        LocalDateTime effectiveStart = (startDate != null) ? startDate : defaultStart;
        LocalDateTime effectiveEnd = (endDate != null) ? endDate : defaultEnd;

        Map<LocalDateTime, Double> trend = salesTrend.getDailySalesTrend(effectiveStart, effectiveEnd);
        return ResponseEntity.ok(trend);
    }

    @GetMapping("/inventory/status/pdf")
    public ResponseEntity<byte[]> generateInventoryStatusPdfReport() throws IOException {

        List<InventoryStatusReport> reportData = inventoryReportService.generateInventoryStatusReport();

        try {
            byte[] pdfBytes = pdfService.generateInventoryPdfReport(reportData).toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "inventory_status_report.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            throw new IOException("Error generating PDF report", e);
        }
    }
}
