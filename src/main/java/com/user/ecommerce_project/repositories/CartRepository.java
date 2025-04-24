package com.user.ecommerce_project.repositories;

import com.user.ecommerce_project.entities.Cart;
import com.user.ecommerce_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
    Optional<Cart> findByUser(User user);
    
    Optional<Cart> findByUserId(Long userId);
    
    boolean existsByUserId(Long userId);
    
    List<Cart> findByTotalPriceGreaterThan(BigDecimal totalPrice);

}
