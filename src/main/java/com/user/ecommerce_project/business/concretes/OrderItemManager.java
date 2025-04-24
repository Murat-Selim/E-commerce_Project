package com.user.ecommerce_project.business.concretes;

import com.user.ecommerce_project.business.abstracts.OrderItemService;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.CreateOrderItemRequest;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.UpdateOrderItemRequest;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderItemResponse;
import com.user.ecommerce_project.business.mapping.ECommerceMapper;
import com.user.ecommerce_project.business.rules.OrderItemBusinessRules;
import com.user.ecommerce_project.entities.OrderItem;
import com.user.ecommerce_project.entities.Product;
import com.user.ecommerce_project.repositories.OrderItemRepository;
import com.user.ecommerce_project.repositories.ProductRepository;
import com.user.ecommerce_project.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemManager implements OrderItemService {
    
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ECommerceMapper mapper;
    private final OrderItemBusinessRules rules;
    
    @Override
    @Transactional
    public OrderItemResponse add(CreateOrderItemRequest createOrderItemRequest) {
        rules.checkIfOrderExists(createOrderItemRequest.getOrderId());
        rules.checkIfProductExists(createOrderItemRequest.getProductId());
        
        Product product = productRepository.findById(createOrderItemRequest.getProductId())
                .orElseThrow(() -> new NotFoundException("Product", createOrderItemRequest.getProductId()));
        
        OrderItem orderItem = mapper.mapCreateOrderItemRequestToOrderItem(createOrderItemRequest);
        
        if (orderItem.getUnitPrice() == null) {
            orderItem.setUnitPrice(product.getPrice());
        }
        
        rules.validateOrderItemInformation(orderItem);
        orderItemRepository.save(orderItem);
        
        return mapper.mapOrderItemToOrderItemResponse(orderItem);
    }
    
    @Override
    @Transactional
    public OrderItemResponse update(Long id, UpdateOrderItemRequest updateOrderItemRequest) {
        rules.checkIfOrderItemExists(id);
        
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow();
        mapper.updateOrderItemFromRequest(updateOrderItemRequest, orderItem);
        orderItemRepository.save(orderItem);
        
        return mapper.mapOrderItemToOrderItemResponse(orderItem);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        rules.checkIfOrderItemExists(id);
        orderItemRepository.deleteById(id);
    }
    
    @Override
    public OrderItemResponse getById(Long id) {
        rules.checkIfOrderItemExists(id);
        
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow();
        return mapper.mapOrderItemToOrderItemResponse(orderItem);
    }
    
    @Override
    public List<OrderItemResponse> getAllByOrderId(Long orderId) {
        rules.checkIfOrderExists(orderId);
        
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return mapper.mapOrderItemListToOrderItemResponseList(orderItems);
    }
    
    @Override
    @Transactional
    public void deleteAllByOrderId(Long orderId) {
        rules.checkIfOrderExists(orderId);
        orderItemRepository.deleteByOrderId(orderId);
    }
}