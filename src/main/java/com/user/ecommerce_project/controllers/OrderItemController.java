package com.user.ecommerce_project.controllers;

import com.user.ecommerce_project.business.abstracts.OrderItemService;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.CreateOrderItemRequest;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.UpdateOrderItemRequest;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderItemResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@AllArgsConstructor
public class OrderItemController {
    
    private final OrderItemService orderItemService;
    
    @PostMapping("/add")
    public ResponseEntity<OrderItemResponse> add(@Valid @RequestBody CreateOrderItemRequest createOrderItemRequest) {
        return new ResponseEntity<>(orderItemService.add(createOrderItemRequest), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateOrderItemRequest updateOrderItemRequest) {
        return new ResponseEntity<>(orderItemService.update(id, updateOrderItemRequest), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(orderItemService.getById(id), HttpStatus.OK);
    }
    
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemResponse>> getAllByOrderId(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderItemService.getAllByOrderId(orderId), HttpStatus.OK);
    }
    
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Void> deleteAllByOrderId(@PathVariable Long orderId) {
        orderItemService.deleteAllByOrderId(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
