package com.user.ecommerce_project.business.rules;

import com.user.ecommerce_project.entities.CartItem;
import com.user.ecommerce_project.entities.Product;
import com.user.ecommerce_project.repositories.CartItemRepository;
import com.user.ecommerce_project.repositories.CartRepository;
import com.user.ecommerce_project.repositories.ProductRepository;
import com.user.ecommerce_project.utils.exceptions.BusinessException;
import com.user.ecommerce_project.utils.exceptions.NotFoundException;
import com.user.ecommerce_project.utils.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartItemBusinessRules {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemBusinessRules(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void checkIfCartItemExists(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new NotFoundException("Cart item", id);
        }
    }
    
    public void checkIfCartExists(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new NotFoundException("Cart", cartId);
        }
    }
    
    public void checkIfProductExists(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product", productId);
        }
    }
    
    public void checkIfProductHasStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NotFoundException("Product", productId));
            
        if (product.getStockQuantity() < quantity) {
            throw new BusinessException("Not enough stock for product: " + product.getName() + 
                    ". Available: " + product.getStockQuantity() + ", Requested: " + quantity);
        }
    }
    
    public void validateCartItemInformation(CartItem cartItem) {
        Map<String, String> validationErrors = new HashMap<>();
        
        if (cartItem.getCart() == null || cartItem.getCart().getId() == null) {
            validationErrors.put("cartId", "Cart item must belong to a cart");
        }
        
        if (cartItem.getProduct() == null || cartItem.getProduct().getId() == null) {
            validationErrors.put("productId", "Cart item must have a product");
        }
        
        if (cartItem.getQuantity() == null || cartItem.getQuantity() <= 0) {
            validationErrors.put("quantity", "Cart item quantity must be greater than zero");
        }
        
        if (!validationErrors.isEmpty()) {
            throw new ValidationException("Cart item validation failed", validationErrors);
        }
    }
}
