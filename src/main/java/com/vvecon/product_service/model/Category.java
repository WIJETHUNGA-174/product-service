package com.vvecon.product_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vvecon.product_service.enums.CategoryGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryGroup categoryGroup;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Product> products = new HashSet<>();
}
