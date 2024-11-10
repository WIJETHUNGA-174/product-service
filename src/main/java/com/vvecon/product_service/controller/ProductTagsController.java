package com.vvecon.product_service.controller;

import com.vvecon.product_service.model.Tags;
import com.vvecon.product_service.service.ProductTagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product_api/v1/product-tags")
public class ProductTagsController {

    private final ProductTagsService productTagsService;

    @PostMapping
    public ResponseEntity<Tags> createProductTag(@RequestBody Tags productTag) {
        return new ResponseEntity<>(productTagsService.createProductTag(productTag), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tags> getProductTagById(@PathVariable Long id) {
        return productTagsService.getProductTagById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Tags>> getAllProductTags() {
        return ResponseEntity.ok(productTagsService.getAllProductTags());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tags> updateProductTag(@PathVariable Long id, @RequestBody Tags productTag) {
        try {
            Tags updatedTag = productTagsService.updateProductTag(id, productTag);
            return ResponseEntity.ok(updatedTag);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductTag(@PathVariable Long id) {
        try {
            productTagsService.deleteProductTag(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
