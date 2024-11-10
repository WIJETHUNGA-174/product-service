package com.vvecon.product_service.dto;

import com.vvecon.product_service.enums.Size;
import com.vvecon.product_service.model.Color;
import com.vvecon.product_service.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Product product;
    private Set<Color> variantColors;
    private Set<Size> variantSizes;
    private int totalStock;
}
