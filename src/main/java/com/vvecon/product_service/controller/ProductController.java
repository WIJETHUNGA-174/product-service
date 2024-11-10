package com.vvecon.product_service.controller;

import com.vvecon.product_service.dto.PageDTO;
import com.vvecon.product_service.dto.ProductRequest;
import com.vvecon.product_service.dto.ProductResponse;
import com.vvecon.product_service.dto.ProductVariantRequest;
import com.vvecon.product_service.enums.Size;
import com.vvecon.product_service.model.Color;
import com.vvecon.product_service.model.Product;
import com.vvecon.product_service.model.ProductVariant;
import com.vvecon.product_service.service.ProductService;
import com.vvecon.product_service.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product_api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductVariantService productVariantService;

    /**
     * Create a new product.
     *
     * @param productRequest the product details
     * @return the created product
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product createdProduct = productService.createProduct(productRequest);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * Retrieve all products.
     *
     * @param pageable pagination information
     * @return paginated list of products
     */
    @GetMapping
    public PageDTO<ProductResponse> getAllProducts(Pageable pageable) {
        Page<ProductResponse> productPage = productService.getAllProduct(pageable);
        return PageDTO.fromPage(productPage);
    }

    /**
     * Create a new color.
     *
     * @param color the color details
     * @return the created color
     */
    @PostMapping("/color")
    @ResponseStatus(HttpStatus.CREATED)
    public Color createColor(@RequestBody Color color) {
        return productService.createColor(color);
    }

    /**
     * Retrieve a specific product by its ID.
     *
     * @param id the product ID
     * @return the product
     */
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    /**
     * Update a product.
     *
     * @param id the product ID
     * @param productRequest updated product details
     * @return the updated product
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }

    /**
     * Delete a product.
     *
     * @param id the product ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    /**
     * Get products by category.
     *
     * @param categoryId the ID of the category
     * @param pageable pagination information
     * @return paginated list of products by category
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<Product>> getProductsByCategory(
            @PathVariable Long categoryId, Pageable pageable) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId, pageable));
    }

    /**
     * Get products by color.
     *
     * @param colorId the ID of the color
     * @param pageable pagination information
     * @return paginated list of products by color
     */
    @GetMapping("/color/{colorId}")
    public ResponseEntity<Page<Product>> getProductsByColor(
            @PathVariable Long colorId, Pageable pageable) {
        return ResponseEntity.ok(productService.getProductsByColor(colorId, pageable));
    }

    /**
     * Get products by size.
     *
     * @param size the size of the product
     * @param pageable pagination information
     * @return paginated list of products by size
     */
    @GetMapping("/size")
    public ResponseEntity<Page<Product>> getProductsBySize(
            @RequestParam Size size, Pageable pageable) {
        return ResponseEntity.ok(productService.getProductsBySize(size, pageable));
    }

    /**
     * Get products by color and size.
     *
     * @param colorId the ID of the color
     * @param size the size of the product
     * @param pageable pagination information
     * @return paginated list of products by color and size
     */
    @GetMapping("/color/{colorId}/size")
    public ResponseEntity<Page<Product>> getProductsByColorAndSize(
            @PathVariable Long colorId, @RequestParam Size size, Pageable pageable) {
        return ResponseEntity.ok(productService.getProductsByColorAndSize(colorId, size, pageable));
    }

    /**
     * Get new arrival products.
     *
     * @param pageable pagination information
     * @return paginated list of new arrival products
     */
    @GetMapping("/new-arrivals")
    public ResponseEntity<Page<Product>> getNewArrivalProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getNewArrivalProducts(pageable));
    }

    /**
     * Get trending products.
     *
     * @param pageable pagination information
     * @return paginated list of trending products
     */
    @GetMapping("/trending")
    public ResponseEntity<Page<Product>> getTrendingProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getTrendingProducts(pageable));
    }

    /**
     * Get popular products.
     *
     * @param pageable pagination information
     * @return paginated list of popular products
     */
    @GetMapping("/popular")
    public ResponseEntity<Page<Product>> getPopularProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getPopularProducts(pageable));
    }
    /**
     * Create a new variant for a product.
     *
     * @param productRequest the product variant details
     * @return the created product variant
     */
    @PostMapping("/{id}/variants")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductVariant createProductVariant(
            @PathVariable Long id,
            @Valid @RequestBody ProductVariantRequest productRequest) {
        return productVariantService.createProductVariant(id, productRequest);
    }

    /**
     * Retrieve a specific product variant by its ID.
     *
     * @param variantId the product variant ID
     * @return the product variant
     */
    @GetMapping("/variants/{variantId}")
    public ResponseEntity<ProductVariant> getProductVariant(@PathVariable Long variantId) {
        return ResponseEntity.ok(productVariantService.getProductVariant(variantId));
    }

    /**
     * Update a product variant.
     *
     * @param id the product ID
     * @param variantId the variant ID
     * @param productRequest updated product variant details
     * @return the updated product variant
     */
    @PutMapping("/{id}/variants/{variantId}")
    public ResponseEntity<ProductVariant> updateProductVariant(
            @PathVariable Long id,
            @PathVariable Long variantId,
            @Valid @RequestBody ProductVariantRequest productRequest) {
        return ResponseEntity.ok(productVariantService.updateProductVariant(id, variantId, productRequest));
    }

    /**
     * Delete a product variant.
     *
     * @param id the product ID
     * @param variantId the variant ID
     */
    @DeleteMapping("/{id}/variants/{variantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductVariant(@PathVariable Long id, @PathVariable Long variantId) {
        productVariantService.deleteProductVariant(id, variantId);
    }

    /**
     * Retrieve all variants for a specific product.
     *
     * @param id the product ID
     * @return a list of product variants for the specified product
     */
    @GetMapping("/{id}/variants")
    public ResponseEntity<List<ProductVariant>> getProductVariantsByProductId(@PathVariable Long id) {
        return ResponseEntity.ok(productVariantService.getProductVariantsByProductId(id));
    }

    /**
     * Get products by tag.
     *
     * @param tagId the ID of the tag
     * @param pageable pagination information
     * @return paginated list of products by tag
     */
    @GetMapping("/tags/{tagId}")
    public ResponseEntity<Page<ProductResponse>> getProductsByTag(@PathVariable Long tagId, Pageable pageable) {
        return ResponseEntity.ok(productService.getProductByTag(tagId, pageable));
    }
}
