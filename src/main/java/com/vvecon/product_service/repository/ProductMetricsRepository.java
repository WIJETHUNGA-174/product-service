package com.vvecon.product_service.repository;

import com.vvecon.product_service.model.ProductMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMetricsRepository extends JpaRepository<ProductMetrics, Long> {}
