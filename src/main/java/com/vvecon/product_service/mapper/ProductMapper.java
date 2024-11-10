package com.vvecon.product_service.mapper;

import com.vvecon.product_service.dto.ProductRequest;
import com.vvecon.product_service.model.Brand;
import com.vvecon.product_service.model.Category;
import com.vvecon.product_service.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProductEntity(ProductRequest dto, Category category, Brand brand) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .skuCode(dto.getSkuCode())
                .price(dto.getPrice())
                .category(category)
                .brand(brand)
                .tags(dto.getTags())
                .imageUrl(dto.getImageUrl())
                .discount(dto.getDiscount())
                .discountStartDate(dto.getDiscountStartDate())
                .discountEndDate(dto.getDiscountEndDate())
                .isFeatured(dto.getIsFeatured())
                .isTrending(dto.getIsTrending())
                .isNewArrival(dto.getIsNewArrival())
                .isPopular(dto.getIsPopular())
                .newArrivalDate(dto.getNewArrivalDate())
                .build();
    }
}
