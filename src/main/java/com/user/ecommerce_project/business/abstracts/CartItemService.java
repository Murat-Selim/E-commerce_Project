package com.user.ecommerce_project.business.abstracts;

import com.user.ecommerce_project.business.dtos.requests.cartRequests.AddCartItemRequest;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.UpdateCartItemRequest;
import com.user.ecommerce_project.business.dtos.responses.cartResponses.CartItemResponse;

import java.util.List;

public interface CartItemService {
    
    CartItemResponse add(AddCartItemRequest addCartItemRequest);
    
    CartItemResponse update(Long id, UpdateCartItemRequest updateCartItemRequest);
    
    void delete(Long id);
    
    CartItemResponse getById(Long id);
    
    List<CartItemResponse> getAllByCartId(Long cartId);
    
    void deleteAllByCartId(Long cartId);
}
