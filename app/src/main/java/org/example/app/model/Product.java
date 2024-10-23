package org.example.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    private String title;
    private int quantity;
    private double price;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
    @CreationTimestamp
    private LocalDateTime created;



}
