package org.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class OperationOnProducts {
    protected double sum;
    protected List<Product> products;
    protected LocalDate date;

}
