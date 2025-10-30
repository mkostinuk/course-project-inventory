package org.example.app.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ImportProducts extends OperationOnProducts {
    private String supplier;

    public ImportProducts(double sum, List<Product> products, LocalDate date) {
        super(sum, products, date);
    }
}
