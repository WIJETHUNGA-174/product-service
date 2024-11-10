package com.vvecon.product_service.repository;

import com.vvecon.product_service.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTagsRepository extends JpaRepository<Tags, Long> {
}