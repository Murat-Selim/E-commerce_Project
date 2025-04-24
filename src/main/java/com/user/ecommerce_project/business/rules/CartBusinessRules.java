package com.user.ecommerce_project.business.rules;

import com.user.ecommerce_project.entities.Cart;
import com.user.ecommerce_project.repositories.CartRepository;
import com.user.ecommerce_project.repositories.UserRepository;
import com.user.ecommerce_project.utils.exceptions.BusinessException;
import com.user.ecommerce_project.utils.exceptions.NotFoundException;
import com.user.ecommerce_project.utils.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartBusinessRules {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartBusinessRules(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public void checkIfCartExists(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new NotFoundException("Cart", id);
        }
    }
    
    public void checkIfUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User", userId);
        }
    }
    
    public void checkIfUserAlreadyHasCart(Long userId) {
        if (cartRepository.existsByUserId(userId)) {
            throw new BusinessException("User already has a cart with id: " + userId);
        }
    }
    
    public void checkIfUserHasCart(Long userId) {
        if (!cartRepository.existsByUserId(userId)) {
            throw new BusinessException("User does not have a cart. User id: " + userId);
        }
    }
    
    public void validateCartInformation(Cart cart) {
        Map<String, String> validationErrors = new HashMap<>();
        
        if (cart.getUser() == null || cart.getUser().getId() == null) {
            validationErrors.put("userId", "Cart must belong to a user");
        }
        
        if (!validationErrors.isEmpty()) {
            throw new ValidationException("Cart validation failed", validationErrors);
        }
    }
}
