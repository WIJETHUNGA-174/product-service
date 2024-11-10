package com.vvecon.product_service.service;

import com.vvecon.product_service.enums.CategoryGroup;
import com.vvecon.product_service.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category updateCategory(Long id, Category updatedCategory);
    void deleteCategory(Long id);
    List<Category> getCategoryByCategoryGroup(CategoryGroup categoryGroup);
}
