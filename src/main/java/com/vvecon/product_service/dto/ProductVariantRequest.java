package com.vvecon.product_service.dto;

import com.vvecon.product_service.enums.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVariantRequest{
        @NotNull(message = "Product ID is required")
        private Long productId;
        @NotNull(message = "Color is required")
        private Long colorId;
        @NotNull(message = "Size is required")
        private Size size;
        private int stock;
        private BigDecimal price;
        private String imageUrl;
}

