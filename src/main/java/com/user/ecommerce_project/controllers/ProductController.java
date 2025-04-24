package com.user.ecommerce_project.controllers;

import com.user.ecommerce_project.business.abstracts.ProductService;
import com.user.ecommerce_project.business.dtos.requests.productRequests.CreateProductRequest;
import com.user.ecommerce_project.business.dtos.requests.productRequests.UpdateProductRequest;
import com.user.ecommerce_project.business.dtos.requests.productRequests.UpdateProductStockRequest;
import com.user.ecommerce_project.business.dtos.responses.productResponses.ProductDetailResponse;
import com.user.ecommerce_project.business.dtos.responses.productResponses.ProductResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> add(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(productService.add(createProductRequest), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateProductRequest updateProductRequest) {
        return new ResponseEntity<>(productService.update(id, updateProductRequest), HttpStatus.OK);
    }
    
    @PatchMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Long id, @Valid @RequestBody UpdateProductStockRequest updateProductStockRequest) {
        productService.updateStock(id, updateProductStockRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }
    
    @GetMapping("/{id}/detail")
    public ResponseEntity<ProductDetailResponse> getDetailById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getDetailById(id), HttpStatus.OK);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getAllByCategoryId(@PathVariable Long categoryId) {
        return new ResponseEntity<>(productService.getAllByCategoryId(categoryId), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchByName(@RequestParam String name) {
        return new ResponseEntity<>(productService.searchByName(name), HttpStatus.OK);
    }
}
