package com.user.ecommerce_project.business.concretes;

import com.user.ecommerce_project.business.abstracts.CartItemService;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.AddCartItemRequest;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.UpdateCartItemRequest;
import com.user.ecommerce_project.business.dtos.responses.cartResponses.CartItemResponse;
import com.user.ecommerce_project.business.mapping.ECommerceMapper;
import com.user.ecommerce_project.business.rules.CartItemBusinessRules;
import com.user.ecommerce_project.entities.CartItem;
import com.user.ecommerce_project.entities.Product;
import com.user.ecommerce_project.repositories.CartItemRepository;
import com.user.ecommerce_project.repositories.ProductRepository;
import com.user.ecommerce_project.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemManager implements CartItemService {
    
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ECommerceMapper mapper;
    private final CartItemBusinessRules rules;
    
    @Override
    @Transactional
    public CartItemResponse add(AddCartItemRequest addCartItemRequest) {
        rules.checkIfCartExists(addCartItemRequest.getCartId());
        rules.checkIfProductExists(addCartItemRequest.getProductId());
        rules.checkIfProductHasStock(addCartItemRequest.getProductId(), addCartItemRequest.getQuantity());
        
        Product product = productRepository.findById(addCartItemRequest.getProductId())
                .orElseThrow(() -> new NotFoundException("Product", addCartItemRequest.getProductId()));
        
        if (cartItemRepository.existsByCartIdAndProductId(
                addCartItemRequest.getCartId(), 
                addCartItemRequest.getProductId())) {
            
            CartItem existingItem = cartItemRepository.findByCartIdAndProductId(
                    addCartItemRequest.getCartId(), 
                    addCartItemRequest.getProductId()).orElseThrow();
            
            int newQuantity = existingItem.getQuantity() + addCartItemRequest.getQuantity();
            rules.checkIfProductHasStock(addCartItemRequest.getProductId(), newQuantity);
            
            existingItem.setQuantity(newQuantity);
            cartItemRepository.save(existingItem);
            
            return mapper.mapCartItemToCartItemResponse(existingItem);
        }
        
        CartItem cartItem = mapper.mapAddCartItemRequestToCartItem(addCartItemRequest);
        cartItem.setUnitPrice(product.getPrice());
        
        rules.validateCartItemInformation(cartItem);
        cartItemRepository.save(cartItem);
        
        return mapper.mapCartItemToCartItemResponse(cartItem);
    }
    
    @Override
    @Transactional
    public CartItemResponse update(Long id, UpdateCartItemRequest updateCartItemRequest) {
        rules.checkIfCartItemExists(id);
        
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow();
        
        if (updateCartItemRequest.getQuantity() != null) {
            rules.checkIfProductHasStock(cartItem.getProduct().getId(), updateCartItemRequest.getQuantity());
        }
        
        mapper.updateCartItemFromRequest(updateCartItemRequest, cartItem);
        cartItemRepository.save(cartItem);
        
        return mapper.mapCartItemToCartItemResponse(cartItem);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        rules.checkIfCartItemExists(id);
        cartItemRepository.deleteById(id);
    }
    
    @Override
    public CartItemResponse getById(Long id) {
        rules.checkIfCartItemExists(id);
        
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow();
        return mapper.mapCartItemToCartItemResponse(cartItem);
    }
    
    @Override
    public List<CartItemResponse> getAllByCartId(Long cartId) {
        rules.checkIfCartExists(cartId);
        
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        return mapper.mapCartItemListToCartItemResponseList(cartItems);
    }
    
    @Override
    @Transactional
    public void deleteAllByCartId(Long cartId) {
        rules.checkIfCartExists(cartId);
        cartItemRepository.deleteByCartId(cartId);
    }
}