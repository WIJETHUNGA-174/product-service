package com.vvecon.product_service.service;

import com.vvecon.product_service.dto.ProductRequest;
import com.vvecon.product_service.dto.ProductResponse;
import com.vvecon.product_service.enums.Size;
import com.vvecon.product_service.model.Color;
import com.vvecon.product_service.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product createProduct(ProductRequest productRequest);
    ProductResponse getProduct(Long id);
    Page<ProductResponse> getAllProduct(Pageable pageable);
    Product updateProduct(Long id, ProductRequest productRequest);
    void deleteProduct(Long id);
    Page<Product> getProductsByCategory(Long categoryId, Pageable pageable);
    Page<Product> getProductsByColor(Long colorId, Pageable pageable);
    Page<Product> getProductsBySize(Size size, Pageable pageable);
    Page<Product> getProductsByColorAndSize(Long colorId, Size size, Pageable pageable);
    Page<Product> getNewArrivalProducts(Pageable pageable);
    Page<Product> getTrendingProducts(Pageable pageable);
    Page<Product> getPopularProducts(Pageable pageable);
    Page<ProductResponse> getProductByTag(Long tagId, Pageable pageable);
    Color createColor(Color color);
}
