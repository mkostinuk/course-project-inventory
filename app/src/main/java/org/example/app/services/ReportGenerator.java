package org.example.app.services;

import org.example.app.model.ExportProduct;

import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator {
    public static void writeExport(ExportProduct exportProduct){
        StringBuilder sb = new StringBuilder();
        sb.append("EXPORT: \n");
        sb.append("Customer: ").append(exportProduct.getCustomer()).append("\n");
        sb.append("Date: ").append(exportProduct.getDate()).append("\n");
        sb.append("Products: \n");
        exportProduct.getProducts().forEach(product -> {
            sb.append(product.getTitle()).append(" - ").append(product.getPrice()).append("\n");
        });
        sb.append("Total: ").append(exportProduct.getSum()).append("\n");
        sb.append("~~~~~~~~~~~~~~~~~~~~~~~~ ~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n");
        try (FileWriter writer = new FileWriter("report.txt", true)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            System.err.println("Failed to write report: " + e.getMessage());
        }
    }
}
