package com.texas.inventorymanagementsystem.service.ReportService;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.texas.inventorymanagementsystem.DTO.ReportDTO.InventoryStatusReport;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfReportService {

    public ByteArrayOutputStream generateInventoryPdfReport(List<InventoryStatusReport> data) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        
        document.open();
        
        // Add title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("Inventory Status Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        
        document.add(Chunk.NEWLINE);
        
        // Create table
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        
        // Add headers
        Stream.of("Product", "SKU", "Category", "Stock", "Price", "Status", "Supplier")
            .forEach(header -> {
                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(new BaseColor(200, 200, 200));
                cell.setPhrase(new Phrase(header));
                table.addCell(cell);
            });

        // Add data
        for (InventoryStatusReport item : data) {
            table.addCell(item.getProductName());
            table.addCell(item.getSku());
            table.addCell(item.getCategory());
            table.addCell(String.valueOf(item.getCurrentStock()));
            table.addCell(String.format("$%.2f", item.getPrice()));
            
            PdfPCell statusCell = new PdfPCell(new Phrase(item.getStatus()));
            if ("Out of Stock".equals(item.getStatus())) {
                statusCell.setBackgroundColor(new BaseColor(255, 150, 150));
            } else if ("Low Stock".equals(item.getStatus())) {
                statusCell.setBackgroundColor(new BaseColor(255, 255, 150));
            } else {
                statusCell.setBackgroundColor(new BaseColor(150, 255, 150));
            }
            table.addCell(statusCell);
            
            table.addCell(item.getSupplier());
        }
        
        document.add(table);
        document.close();
        
        return out;
    }
}