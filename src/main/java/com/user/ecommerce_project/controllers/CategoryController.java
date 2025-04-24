package com.user.ecommerce_project.controllers;

import com.user.ecommerce_project.business.abstracts.CategoryService;
import com.user.ecommerce_project.business.dtos.requests.categoryRequests.CreateCategoryRequest;
import com.user.ecommerce_project.business.dtos.requests.categoryRequests.UpdateCategoryRequest;
import com.user.ecommerce_project.business.dtos.responses.categoryResponses.CategoryResponse;
import com.user.ecommerce_project.business.dtos.responses.categoryResponses.CategoryWithProductsResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    
    private final CategoryService categoryService;
    
    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> add(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        return new ResponseEntity<>(categoryService.add(createCategoryRequest), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return new ResponseEntity<>(categoryService.update(id, updateCategoryRequest), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
    }
    
    @GetMapping("/{id}/with-products")
    public ResponseEntity<CategoryWithProductsResponse> getDetailById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getDetailById(id), HttpStatus.OK);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }
}
