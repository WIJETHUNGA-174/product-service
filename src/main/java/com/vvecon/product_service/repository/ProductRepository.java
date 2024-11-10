package com.vvecon.product_service.repository;

import com.vvecon.product_service.enums.Size;
import com.vvecon.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @NonNull
    Page<Product> findAll(@NonNull Pageable pageable);

    Page<Product> findByVariantsColorId(Long colorId, Pageable pageable);
    Page<Product> findByVariantsSize(Size size, Pageable pageable);
    Page<Product> findByVariantsColorIdAndVariantsSize(Long colorId, Size size, Pageable pageable);

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Product> findByIsNewArrivalTrue(Pageable pageable);

    Page<Product> findByIsTrendingTrue(Pageable pageable);

    Page<Product> findByIsPopularTrue(Pageable pageable);

    Page<Product> findByTagsId(Long tagId, Pageable pageable);
}
