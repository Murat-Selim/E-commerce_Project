package com.user.ecommerce_project.business.concretes;

import com.user.ecommerce_project.business.abstracts.CategoryService;
import com.user.ecommerce_project.business.dtos.requests.categoryRequests.CreateCategoryRequest;
import com.user.ecommerce_project.business.dtos.requests.categoryRequests.UpdateCategoryRequest;
import com.user.ecommerce_project.business.dtos.responses.categoryResponses.CategoryResponse;
import com.user.ecommerce_project.business.dtos.responses.categoryResponses.CategoryWithProductsResponse;
import com.user.ecommerce_project.business.mapping.ECommerceMapper;
import com.user.ecommerce_project.business.rules.CategoryBusinessRules;
import com.user.ecommerce_project.entities.Category;
import com.user.ecommerce_project.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final ECommerceMapper mapper;
    private final CategoryBusinessRules rules;
    
    @Override
    public CategoryResponse add(CreateCategoryRequest createCategoryRequest) {
        rules.checkIfCategoryNameExists(createCategoryRequest.getName());
        
        Category category = mapper.mapCreateCategoryRequestToCategory(createCategoryRequest);
        rules.validateCategoryInformation(category);
        categoryRepository.save(category);
        
        return mapper.mapCategoryToCategoryResponse(category);
    }
    
    @Override
    public CategoryResponse update(Long id, UpdateCategoryRequest updateCategoryRequest) {
        rules.checkIfCategoryExists(id);
        
        if (updateCategoryRequest.getName() != null) {
            rules.checkIfCategoryNameExistsForUpdate(id, updateCategoryRequest.getName());
        }
        
        Category category = categoryRepository.findById(id).orElseThrow();
        mapper.updateCategoryFromRequest(updateCategoryRequest, category);
        categoryRepository.save(category);
        
        return mapper.mapCategoryToCategoryResponse(category);
    }
    
    @Override
    public void delete(Long id) {
        rules.checkIfCategoryExists(id);
        rules.checkIfCategoryHasProducts(id);
        
        categoryRepository.deleteById(id);
    }
    
    @Override
    public CategoryResponse getById(Long id) {
        rules.checkIfCategoryExists(id);
        
        Category category = categoryRepository.findById(id).orElseThrow();
        return mapper.mapCategoryToCategoryResponse(category);
    }
    
    @Override
    public CategoryWithProductsResponse getDetailById(Long id) {
        rules.checkIfCategoryExists(id);
        
        Category category = categoryRepository.findById(id).orElseThrow();
        return mapper.mapCategoryToCategoryWithProductsResponse(category);
    }
    
    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return mapper.mapCategoryListToCategoryResponseList(categories);
    }
}
