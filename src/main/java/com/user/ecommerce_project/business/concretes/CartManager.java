package com.user.ecommerce_project.business.concretes;

import com.user.ecommerce_project.business.abstracts.CartService;
import com.user.ecommerce_project.business.abstracts.UserService;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.CreateCartRequest;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.UpdateCartRequest;
import com.user.ecommerce_project.business.dtos.responses.cartResponses.CartResponse;
import com.user.ecommerce_project.business.mapping.ECommerceMapper;
import com.user.ecommerce_project.business.rules.CartBusinessRules;
import com.user.ecommerce_project.entities.Cart;
import com.user.ecommerce_project.repositories.CartItemRepository;
import com.user.ecommerce_project.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CartManager implements CartService {
    
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ECommerceMapper mapper;
    private final CartBusinessRules rules;
    private final UserService userService;
    
    @Override
    @Transactional
    public CartResponse add(CreateCartRequest createCartRequest) {
        rules.checkIfUserExists(createCartRequest.getUserId());
        rules.checkIfUserAlreadyHasCart(createCartRequest.getUserId());
        
        Cart cart = mapper.mapCreateCartRequestToCart(createCartRequest);
        rules.validateCartInformation(cart);
        cartRepository.save(cart);
        
        return mapper.mapCartToCartResponse(cart);
    }
    
    @Override
    @Transactional
    public CartResponse update(Long id, UpdateCartRequest updateCartRequest) {
        rules.checkIfCartExists(id);
        
        Cart cart = cartRepository.findById(id).orElseThrow();
        mapper.updateCartFromRequest(updateCartRequest, cart);
        cartRepository.save(cart);
        
        return mapper.mapCartToCartResponse(cart);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        rules.checkIfCartExists(id);
        
        cartItemRepository.deleteById(id);
        cartRepository.deleteById(id);
    }
    
    @Override
    public CartResponse getById(Long id) {
        rules.checkIfCartExists(id);
        
        Cart cart = cartRepository.findById(id).orElseThrow();
        return mapper.mapCartToCartResponse(cart);
    }
    
    @Override
    public CartResponse getByUserId(Long userId) {
        rules.checkIfUserExists(userId);
        rules.checkIfUserHasCart(userId);
        
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        return mapper.mapCartToCartResponse(cart);
    }
    
    @Override
    @Transactional
    public void clearCart(Long id) {
        rules.checkIfCartExists(id);
        
        cartItemRepository.deleteById(id);
    }
}
