package com.vvecon.product_service.service_impl;

import com.vvecon.product_service.dto.ProductRequest;
import com.vvecon.product_service.dto.ProductResponse;
import com.vvecon.product_service.enums.Size;
import com.vvecon.product_service.mapper.ProductMapper;
import com.vvecon.product_service.model.*;
import com.vvecon.product_service.repository.*;
import com.vvecon.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public Product createProduct(ProductRequest productRequest) {
        log.info("Creating product: {}", productRequest.getName());
        var category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        Product product = productMapper.toProductEntity(productRequest, category, null);
        product.setCreatedAt(LocalDate.now());
        productRepository.save(product);
        return product;
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Set<Color> colors = product.getVariants().stream()
                .map(ProductVariant::getColor)
                .collect(Collectors.toSet());
        Set<Size> sizes = product.getVariants().stream()
                .map(ProductVariant::getSize)
                .collect(Collectors.toSet());
        int totalStock = product.getVariants().stream()
                .mapToInt(ProductVariant::getStock)
                .sum();

        return new ProductResponse(product, colors, sizes, totalStock);
    }

    @Override
    public Page<ProductResponse> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> getProduct(product.getId()));
    }

    @Override
    @Transactional
    public Product updateProduct(Long productId, ProductRequest updatedProduct) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        BeanUtils.copyProperties(updatedProduct, product, getNullPropertyNames(updatedProduct));
        productRepository.save(product);
        return product;
    }

    private <T> String[] getNullPropertyNames(T source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : src.getPropertyDescriptors()) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        return emptyNames.toArray(new String[0]);
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    @Override
    public Color createColor(Color color) {
        return colorRepository.save(color);
    }

    @Override
    @Transactional
    public Page<Product> getProductsByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable);
    }

    @Override
    @Transactional
    public Page<Product> getProductsByColor(Long colorId, Pageable pageable) {
        return productRepository.findByVariantsColorId(colorId, pageable);
    }

    @Override
    @Transactional
    public Page<Product> getProductsBySize(Size size, Pageable pageable) {
        return productRepository.findByVariantsSize(size, pageable);
    }

    @Override
    @Transactional
    public Page<Product> getProductsByColorAndSize(Long colorId, Size size, Pageable pageable) {
        return productRepository.findByVariantsColorIdAndVariantsSize(colorId, size, pageable);
    }

    @Override
    @Transactional
    public Page<Product> getNewArrivalProducts(Pageable pageable) {
        return productRepository.findByIsNewArrivalTrue(pageable);
    }

    @Override
    @Transactional
    public Page<Product> getTrendingProducts(Pageable pageable) {
        return productRepository.findByIsTrendingTrue(pageable);
    }

    @Override
    @Transactional
    public Page<Product> getPopularProducts(Pageable pageable) {
        return productRepository.findByIsPopularTrue(pageable);
    }

    public Page<ProductResponse> getProductByTag(Long tagId, Pageable pageable) {
        return productRepository.findByTagsId(tagId, pageable)
                .map(product -> getProduct(product.getId()));
    }
}
