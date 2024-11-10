package com.vvecon.product_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_metrics")
@Data
public class ProductMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int weeklyViews;
    private int weeklySales;
    private int monthlySales;
    private double averageRating;
}
