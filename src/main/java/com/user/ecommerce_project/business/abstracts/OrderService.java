package com.user.ecommerce_project.business.abstracts;

import com.user.ecommerce_project.business.dtos.requests.orderRequests.CreateOrderRequest;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.UpdateOrderRequest;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderDetailResponse;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderResponse;

import java.util.List;

public interface OrderService {
    
    OrderResponse add(CreateOrderRequest createOrderRequest);
    
    OrderResponse update(Long id, UpdateOrderRequest updateOrderRequest);
    
    void delete(Long id);
    
    OrderResponse getById(Long id);
    
    OrderDetailResponse getDetailById(Long id);
    
    List<OrderResponse> getAllByUserId(Long userId);
    
    List<OrderResponse> getAll();
}
