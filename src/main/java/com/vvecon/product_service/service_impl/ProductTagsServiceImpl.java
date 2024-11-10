package com.vvecon.product_service.service_impl;

import com.vvecon.product_service.model.Tags;
import com.vvecon.product_service.repository.ProductTagsRepository;
import com.vvecon.product_service.service.ProductTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTagsServiceImpl implements ProductTagsService {

    private final ProductTagsRepository productTagsRepository;

    @Autowired
    public ProductTagsServiceImpl(ProductTagsRepository productTagsRepository) {
        this.productTagsRepository = productTagsRepository;
    }

    @Override
    public Tags createProductTag(Tags productTag) {
        return productTagsRepository.save(productTag);
    }

    @Override
    public Optional<Tags> getProductTagById(Long id) {
        return productTagsRepository.findById(id);
    }

    @Override
    public List<Tags> getAllProductTags() {
        return productTagsRepository.findAll();
    }

    @Override
    public Tags updateProductTag(Long id, Tags productTag) {
        return productTagsRepository.findById(id)
                .map(existingTag -> {
                    existingTag.setName(productTag.getName());
                    existingTag.setShortName(productTag.getShortName());
                    existingTag.setShowOnProduct(productTag.isShowOnProduct());
                    existingTag.setColor(productTag.getColor());
                    existingTag.setDescription(productTag.getDescription());
                    return productTagsRepository.save(existingTag);
                })
                .orElseThrow(() -> new RuntimeException("ProductTag not found with id " + id));
    }

    @Override
    public void deleteProductTag(Long id) {
        if (productTagsRepository.existsById(id)) {
            productTagsRepository.deleteById(id);
        } else {
            throw new RuntimeException("ProductTag not found with id " + id);
        }
    }
}

