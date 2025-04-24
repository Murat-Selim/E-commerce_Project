package com.user.ecommerce_project.repositories;

import com.user.ecommerce_project.entities.Category;
import com.user.ecommerce_project.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategory(Category category);
    
    List<Product> findByNameContainingIgnoreCase(String name);
    
    List<Product> findByCategoryId(Long categoryId);
    
    List<Product> findByStockQuantityLessThan(Integer quantity);
    
    List<Product> findByStockQuantityGreaterThan(Integer quantity);
    
    boolean existsByName(String name);
    
    boolean existsByNameAndIdNot(String name, Long id);

}
