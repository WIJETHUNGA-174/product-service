package com.vvecon.product_service.repository;

import com.vvecon.product_service.enums.CategoryGroup;
import com.vvecon.product_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryGroup(CategoryGroup categoryGroup);
}
