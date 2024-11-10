package com.vvecon.product_service.dto;

import com.vvecon.product_service.model.Tags;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProductRequest {
    @NotBlank(message = "Product name is required")
    private String name;
    private String description;
    @NotBlank(message = "SKU code is required")
    private String skuCode;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    private List<Tags> tags;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
    private Long brandId;
    private String imageUrl;
    private Double discount;
    private Date discountStartDate;
    private Date discountEndDate;
    private Boolean isFeatured;
    private Date newArrivalDate;
    private Boolean isNewArrival;
    private Boolean isPopular;
    private Boolean isTrending;
}
