package com.project.ecommerceapp.repository;

import com.project.ecommerceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByProductsName(String name);

    List<Product> findByBrandAndProductsName(String brand, String name);

    Long countByBrandAndProductsName(String brand, String name);
}
