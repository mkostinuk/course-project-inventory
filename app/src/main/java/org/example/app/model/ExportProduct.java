package org.example.app.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ExportProduct extends OperationOnProducts {
    private String customer;

    public ExportProduct(double sum, List<Product> products, LocalDate date) {
        super(sum, products, date);
    }
}
