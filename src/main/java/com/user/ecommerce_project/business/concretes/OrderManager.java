package com.user.ecommerce_project.business.concretes;

import com.user.ecommerce_project.business.abstracts.CartService;
import com.user.ecommerce_project.business.abstracts.OrderItemService;
import com.user.ecommerce_project.business.abstracts.OrderService;
import com.user.ecommerce_project.business.abstracts.UserService;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.CreateOrderRequest;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.UpdateOrderRequest;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderDetailResponse;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderResponse;
import com.user.ecommerce_project.business.mapping.ECommerceMapper;
import com.user.ecommerce_project.business.rules.OrderBusinessRules;
import com.user.ecommerce_project.entities.Order;
import com.user.ecommerce_project.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    
    private final OrderRepository orderRepository;
    private final ECommerceMapper mapper;
    private final OrderBusinessRules rules;
    private final UserService userService;
    private final CartService cartService;
    private final OrderItemService orderItemService;
    
    @Override
    @Transactional
    public OrderResponse add(CreateOrderRequest createOrderRequest) {
        rules.checkIfUserExists(createOrderRequest.getUserId());
        rules.checkIfCartIsEmpty(createOrderRequest.getUserId());
        
        Order order = mapper.mapCreateOrderRequestToOrder(createOrderRequest);
        rules.validateOrderInformation(order);
        orderRepository.save(order);
        
        return mapper.mapOrderToOrderResponse(order);
    }
    
    @Override
    @Transactional
    public OrderResponse update(Long id, UpdateOrderRequest updateOrderRequest) {
        rules.checkIfOrderExists(id);
        
        Order order = orderRepository.findById(id).orElseThrow();
        mapper.updateOrderFromRequest(updateOrderRequest, order);
        orderRepository.save(order);
        
        return mapper.mapOrderToOrderResponse(order);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        rules.checkIfOrderExists(id);
        
        orderItemService.deleteAllByOrderId(id);
        orderRepository.deleteById(id);
    }
    
    @Override
    public OrderResponse getById(Long id) {
        rules.checkIfOrderExists(id);
        
        Order order = orderRepository.findById(id).orElseThrow();
        return mapper.mapOrderToOrderResponse(order);
    }
    
    @Override
    public OrderDetailResponse getDetailById(Long id) {
        rules.checkIfOrderExists(id);
        
        Order order = orderRepository.findById(id).orElseThrow();
        return mapper.mapOrderToOrderDetailResponse(order);
    }
    
    @Override
    public List<OrderResponse> getAllByUserId(Long userId) {
        rules.checkIfUserExists(userId);
        
        List<Order> orders = orderRepository.findByUserId(userId);
        return mapper.mapOrderListToOrderResponseList(orders);
    }
    
    @Override
    public List<OrderResponse> getAll() {
        List<Order> orders = orderRepository.findAll();
        return mapper.mapOrderListToOrderResponseList(orders);
    }
}
