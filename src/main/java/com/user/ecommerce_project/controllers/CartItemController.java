package com.user.ecommerce_project.controllers;

import com.user.ecommerce_project.business.abstracts.CartItemService;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.AddCartItemRequest;
import com.user.ecommerce_project.business.dtos.requests.cartRequests.UpdateCartItemRequest;
import com.user.ecommerce_project.business.dtos.responses.cartResponses.CartItemResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
@AllArgsConstructor
public class CartItemController {
    
    private final CartItemService cartItemService;
    
    @PostMapping("/add")
    public ResponseEntity<CartItemResponse> add(@Valid @RequestBody AddCartItemRequest addCartItemRequest) {
        return new ResponseEntity<>(cartItemService.add(addCartItemRequest), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CartItemResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateCartItemRequest updateCartItemRequest) {
        return new ResponseEntity<>(cartItemService.update(id, updateCartItemRequest), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CartItemResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(cartItemService.getById(id), HttpStatus.OK);
    }
    
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItemResponse>> getAllByCartId(@PathVariable Long cartId) {
        return new ResponseEntity<>(cartItemService.getAllByCartId(cartId), HttpStatus.OK);
    }
    
    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<Void> deleteAllByCartId(@PathVariable Long cartId) {
        cartItemService.deleteAllByCartId(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
