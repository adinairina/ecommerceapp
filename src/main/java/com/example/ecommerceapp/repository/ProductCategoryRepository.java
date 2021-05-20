package com.example.ecommerceapp.repository;

import com.example.ecommerceapp.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    ProductCategory findById(long id);
}