package com.user.ecommerce_project.business.abstracts;

import com.user.ecommerce_project.business.dtos.requests.productRequests.CreateProductRequest;
import com.user.ecommerce_project.business.dtos.requests.productRequests.UpdateProductRequest;
import com.user.ecommerce_project.business.dtos.requests.productRequests.UpdateProductStockRequest;
import com.user.ecommerce_project.business.dtos.responses.productResponses.ProductDetailResponse;
import com.user.ecommerce_project.business.dtos.responses.productResponses.ProductResponse;

import java.util.List;

public interface ProductService {
    
    ProductResponse add(CreateProductRequest createProductRequest);
    
    ProductResponse update(Long id, UpdateProductRequest updateProductRequest);
    
    void delete(Long id);
    
    void updateStock(Long id, UpdateProductStockRequest updateProductStockRequest);
    
    ProductResponse getById(Long id);
    
    ProductDetailResponse getDetailById(Long id);
    
    List<ProductResponse> getAll();
    
    List<ProductResponse> getAllByCategoryId(Long categoryId);
    
    List<ProductResponse> searchByName(String name);
}
