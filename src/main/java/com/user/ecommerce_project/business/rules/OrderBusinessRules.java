package com.user.ecommerce_project.business.rules;

import com.user.ecommerce_project.entities.Cart;
import com.user.ecommerce_project.entities.Order;
import com.user.ecommerce_project.repositories.CartRepository;
import com.user.ecommerce_project.repositories.OrderRepository;
import com.user.ecommerce_project.repositories.UserRepository;
import com.user.ecommerce_project.utils.exceptions.BusinessException;
import com.user.ecommerce_project.utils.exceptions.NotFoundException;
import com.user.ecommerce_project.utils.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderBusinessRules {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderBusinessRules(OrderRepository orderRepository, 
                            UserRepository userRepository,
                            CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public void checkIfOrderExists(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new NotFoundException("Order", id);
        }
    }
    
    public void checkIfUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User", userId);
        }
    }
    
    public void checkIfCartIsEmpty(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
            .orElseThrow(() -> new NotFoundException("Cart", "userId", userId));
            
        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            throw new BusinessException("Cannot place order with empty cart");
        }
    }
    
    public void checkIfOrderBelongsToUser(Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new NotFoundException("Order", orderId));
            
        if (!order.getUser().getId().equals(userId)) {
            throw new BusinessException("This order does not belong to the specified user");
        }
    }
    
    public void validateOrderInformation(Order order) {
        Map<String, String> validationErrors = new HashMap<>();
        
        if (order.getUser() == null || order.getUser().getId() == null) {
            validationErrors.put("userId", "Order must belong to a user");
        }
        
        if (order.getTotalPrice() == null) {
            validationErrors.put("totalPrice", "Order total amount must be greater than zero");
        }
        
        if (order.getShippingAddress() == null || order.getShippingAddress().isEmpty()) {
            validationErrors.put("shippingAddress", "Shipping address must be provided");
        }
        
        if (!validationErrors.isEmpty()) {
            throw new ValidationException("Order validation failed", validationErrors);
        }
    }
}
