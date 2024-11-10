package com.vvecon.product_service.service;

import com.vvecon.product_service.dto.ProductVariantRequest;
import com.vvecon.product_service.model.ProductVariant;

import java.util.List;

public interface ProductVariantService {
    ProductVariant createProductVariant(Long productId, ProductVariantRequest productRequest);
    ProductVariant getProductVariant(Long variantId);
    ProductVariant updateProductVariant(Long productId, Long variantId, ProductVariantRequest productRequest);
    void deleteProductVariant(Long productId, Long variantId);
    List<ProductVariant> getProductVariantsByProductId(Long productId);
}