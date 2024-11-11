package org.example.app.services;

import org.example.app.model.ExportProduct;
import org.example.app.model.ImportProducts;
import org.example.app.model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReportGenerator {

    public static void writeExport(ExportProduct exportProduct) {
        writeReport(
                "EXPORT",
                "Customer: " + exportProduct.getCustomer(),
                exportProduct.getDate(),
                exportProduct.getProducts(),
                exportProduct.getSum()
        );
    }

    public static void writeImport(ImportProducts importProducts) {
        writeReport(
                "IMPORT",
                "Supplier: " + importProducts.getSupplier(),
                importProducts.getDate(),
                importProducts.getProducts(),
                importProducts.getSum()
        );
    }

    private static void writeReport(
            String type,
            String participant,
            LocalDate date,
            List<Product> products,
            double sum
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append(type).append(":\n");
        sb.append(participant).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Products:\n");

        products.forEach(product ->
                sb.append(product.getTitle())
                        .append(" - ")
                        .append(product.getPrice())
                        .append(product.getQuantity())
                        .append("\n")
        );

        sb.append("Total: ").append(sum).append("\n");
        sb.append("~~~~~~~~~~~~~~~~~~~~~~~~ ~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        try (FileWriter writer = new FileWriter("report.txt", true)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            System.err.println("Failed to write report: " + e.getMessage());
        }
    }
}

