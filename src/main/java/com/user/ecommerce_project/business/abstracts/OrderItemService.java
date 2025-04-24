package com.user.ecommerce_project.business.abstracts;

import com.user.ecommerce_project.business.dtos.requests.orderRequests.CreateOrderItemRequest;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.UpdateOrderItemRequest;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    
    OrderItemResponse add(CreateOrderItemRequest createOrderItemRequest);
    
    OrderItemResponse update(Long id, UpdateOrderItemRequest updateOrderItemRequest);
    
    void delete(Long id);
    
    OrderItemResponse getById(Long id);
    
    List<OrderItemResponse> getAllByOrderId(Long orderId);
    
    void deleteAllByOrderId(Long orderId);
}
