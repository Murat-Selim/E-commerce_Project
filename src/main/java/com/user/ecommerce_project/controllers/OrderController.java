package com.user.ecommerce_project.controllers;

import com.user.ecommerce_project.business.abstracts.OrderService;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.CreateOrderRequest;
import com.user.ecommerce_project.business.dtos.requests.orderRequests.UpdateOrderRequest;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderDetailResponse;
import com.user.ecommerce_project.business.dtos.responses.orderResponses.OrderResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping("/add")
    public ResponseEntity<OrderResponse> add(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        return new ResponseEntity<>(orderService.add(createOrderRequest), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateOrderRequest updateOrderRequest) {
        return new ResponseEntity<>(orderService.update(id, updateOrderRequest), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
    }
    
    @GetMapping("/{id}/detail")
    public ResponseEntity<OrderDetailResponse> getDetailById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getDetailById(id), HttpStatus.OK);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getAllByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getAllByUserId(userId), HttpStatus.OK);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponse>> getAll() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }
}
