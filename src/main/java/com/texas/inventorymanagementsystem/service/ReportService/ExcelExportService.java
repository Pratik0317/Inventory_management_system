package com.texas.inventorymanagementsystem.service.ReportService;

import com.texas.inventorymanagementsystem.DTO.ReportDTO.InventoryStatusReport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {
    public ByteArrayOutputStream exportInventoryToExcel(List<InventoryStatusReport> data) throws IOException {
        try(Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("Inventory Status Report");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"Product Name", "SKU", "Category", "Current Stock", "Price"
                    , "Status", "Suppliers"};
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }
            int rowNum = 1;
            for (InventoryStatusReport report : data) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(report.getProductName());
                row.createCell(1).setCellValue(report.getSku());
                row.createCell(2).setCellValue(report.getCategory());
                row.createCell(3).setCellValue(report.getCurrentStock());
                row.createCell(4).setCellValue(report.getPrice());
                row.createCell(5).setCellValue(report.getStatus());
                row.createCell(6).setCellValue(report.getSupplier());

                CellStyle cellStyle = workbook.createCellStyle();
                if ("Out of Stock".equals(report.getStatus())) {
                    cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                } else if ("Low Stock".equals(report.getStatus())) {
                    cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                } else {
                    cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                }
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                row.getCell(5).setCellStyle(cellStyle);
            }
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(out);
            return out;
        }
    }
}
