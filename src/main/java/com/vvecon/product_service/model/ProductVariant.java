package com.vvecon.product_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vvecon.product_service.enums.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Product product;

    @Enumerated(EnumType.STRING)
    private Size size;
    private int stock;
    private BigDecimal price;
    private String imageUrl;
}

