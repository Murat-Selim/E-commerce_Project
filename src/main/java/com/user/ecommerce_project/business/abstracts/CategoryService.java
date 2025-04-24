package com.user.ecommerce_project.business.abstracts;

import com.user.ecommerce_project.business.dtos.requests.categoryRequests.CreateCategoryRequest;
import com.user.ecommerce_project.business.dtos.requests.categoryRequests.UpdateCategoryRequest;
import com.user.ecommerce_project.business.dtos.responses.categoryResponses.CategoryResponse;
import com.user.ecommerce_project.business.dtos.responses.categoryResponses.CategoryWithProductsResponse;

import java.util.List;

public interface CategoryService {
    
    CategoryResponse add(CreateCategoryRequest createCategoryRequest);
    
    CategoryResponse update(Long id, UpdateCategoryRequest updateCategoryRequest);
    
    void delete(Long id);
    
    CategoryResponse getById(Long id);
    
    CategoryWithProductsResponse getDetailById(Long id);
    
    List<CategoryResponse> getAll();
}
