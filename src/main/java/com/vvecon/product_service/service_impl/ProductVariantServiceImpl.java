package com.vvecon.product_service.service_impl;

import com.vvecon.product_service.dto.ProductVariantRequest;
import com.vvecon.product_service.model.Product;
import com.vvecon.product_service.model.ProductVariant;
import com.vvecon.product_service.repository.ProductRepository;
import com.vvecon.product_service.repository.ProductVariantRepository;
import com.vvecon.product_service.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductVariant createProductVariant(Long productId, ProductVariantRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));
        ProductVariant variant = new ProductVariant();
        variant.setProduct(product);
        // Map other fields from productRequest to variant
        return productVariantRepository.save(variant);
    }

    @Override
    public ProductVariant getProductVariant(Long variantId) {
        return productVariantRepository.findById(variantId)
                .orElseThrow(() -> new IllegalArgumentException("Product variant not found with ID: " + variantId));
    }

    @Override
    public ProductVariant updateProductVariant(Long productId, Long variantId, ProductVariantRequest productRequest) {
        ProductVariant variant = getProductVariant(variantId);
        // Update variant fields based on productRequest
        return productVariantRepository.save(variant);
    }

    @Override
    public void deleteProductVariant(Long productId, Long variantId) {
        ProductVariant variant = getProductVariant(variantId);
        productVariantRepository.delete(variant);
    }

    @Override
    public List<ProductVariant> getProductVariantsByProductId(Long productId) {
        return productVariantRepository.findByProductId(productId);
    }
}
