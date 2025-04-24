package com.user.ecommerce_project.repositories;

import com.user.ecommerce_project.entities.Cart;
import com.user.ecommerce_project.entities.CartItem;
import com.user.ecommerce_project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    List<CartItem> findByCart(Cart cart);
    
    List<CartItem> findByCartId(Long cartId);
    
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
    
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
    
    boolean existsByCartIdAndProductId(Long cartId, Long productId);
    
    List<CartItem> findByProduct(Product product);
    
    List<CartItem> findByProductId(Long productId);
    
    void deleteByCart(Cart cart);
    
    void deleteByCartId(Long cartId);
}
