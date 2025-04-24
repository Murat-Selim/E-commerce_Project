package com.user.ecommerce_project.controllers;

import com.user.ecommerce_project.business.abstracts.CartService;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.CreateCartRequest;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.UpdateCartRequest;
import com.user.ecommerce_project.business.dtos.responses.cartResponses.CartResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartController {
    
    private final CartService cartService;
    
    @PostMapping("/add")
    public ResponseEntity<CartResponse> add(@Valid @RequestBody CreateCartRequest createCartRequest) {
        return new ResponseEntity<>(cartService.add(createCartRequest), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CartResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateCartRequest updateCartRequest) {
        return new ResponseEntity<>(cartService.update(id, updateCartRequest), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getById(id), HttpStatus.OK);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartResponse> getByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.getByUserId(userId), HttpStatus.OK);
    }
    
    @PostMapping("/{id}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long id) {
        cartService.clearCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
