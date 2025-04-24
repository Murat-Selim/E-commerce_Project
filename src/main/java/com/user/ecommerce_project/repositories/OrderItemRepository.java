package com.user.ecommerce_project.repositories;

import com.user.ecommerce_project.entities.Order;
import com.user.ecommerce_project.entities.OrderItem;
import com.user.ecommerce_project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
    List<OrderItem> findByOrder(Order order);
    
    List<OrderItem> findByOrderId(Long orderId);
    
    List<OrderItem> findByProduct(Product product);
    
    List<OrderItem> findByProductId(Long productId);
    
    @Transactional
    void deleteByOrderId(Long orderId);
    
    @Transactional
    void deleteByOrder(Order order);
}
