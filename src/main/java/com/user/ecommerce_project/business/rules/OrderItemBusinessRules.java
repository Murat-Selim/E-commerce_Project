package com.user.ecommerce_project.business.rules;

import com.user.ecommerce_project.entities.OrderItem;
import com.user.ecommerce_project.repositories.OrderItemRepository;
import com.user.ecommerce_project.repositories.OrderRepository;
import com.user.ecommerce_project.repositories.ProductRepository;
import com.user.ecommerce_project.utils.exceptions.NotFoundException;
import com.user.ecommerce_project.utils.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderItemBusinessRules {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemBusinessRules(OrderItemRepository orderItemRepository, 
                                OrderRepository orderRepository,
                                ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void checkIfOrderItemExists(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new NotFoundException("Order item", id);
        }
    }
    
    public void checkIfOrderExists(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order", orderId);
        }
    }
    
    public void checkIfProductExists(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product", productId);
        }
    }
    
    public void validateOrderItemInformation(OrderItem orderItem) {
        Map<String, String> validationErrors = new HashMap<>();
        
        if (orderItem.getOrder() == null || orderItem.getOrder().getId() == null) {
            validationErrors.put("orderId", "Order item must belong to an order");
        }
        
        if (orderItem.getProduct() == null || orderItem.getProduct().getId() == null) {
            validationErrors.put("productId", "Order item must have a product");
        }
        
        if (orderItem.getQuantity() == null || orderItem.getQuantity() <= 0) {
            validationErrors.put("quantity", "Order item quantity must be greater than zero");
        }
        
        if (orderItem.getUnitPrice() == null) {
            validationErrors.put("unitPrice", "Order item price must be greater than zero");
        }
        
        if (!validationErrors.isEmpty()) {
            throw new ValidationException("Order item validation failed", validationErrors);
        }
    }
}
