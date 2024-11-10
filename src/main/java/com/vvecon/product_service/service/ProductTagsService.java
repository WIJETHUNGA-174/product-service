package com.vvecon.product_service.service;

import com.vvecon.product_service.model.Tags;

import java.util.List;
import java.util.Optional;

public interface ProductTagsService {
    Tags createProductTag(Tags productTag);
    Optional<Tags> getProductTagById(Long id);
    List<Tags> getAllProductTags();
    Tags updateProductTag(Long id, Tags productTag);
    void deleteProductTag(Long id);
}

