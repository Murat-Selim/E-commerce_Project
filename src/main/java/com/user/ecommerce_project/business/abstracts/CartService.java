package com.user.ecommerce_project.business.abstracts;

import com.user.ecommerce_project.business.dtos.requests.cartRequests.CreateCartRequest;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.UpdateCartRequest;
import com.user.ecommerce_project.business.dtos.responses.cartResponses.CartResponse;

public interface CartService {
    
    CartResponse add(CreateCartRequest createCartRequest);
    
    CartResponse update(Long id, UpdateCartRequest updateCartRequest);
    
    void delete(Long id);
    
    CartResponse getById(Long id);
    
    CartResponse getByUserId(Long userId);
    
    void clearCart(Long id);
}
