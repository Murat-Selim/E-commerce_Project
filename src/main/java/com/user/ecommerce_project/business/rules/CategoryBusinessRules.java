package com.user.ecommerce_project.business.rules;

import com.user.ecommerce_project.entities.Category;
import com.user.ecommerce_project.repositories.CategoryRepository;
import com.user.ecommerce_project.utils.exceptions.BusinessException;
import com.user.ecommerce_project.utils.exceptions.NotFoundException;
import com.user.ecommerce_project.utils.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryBusinessRules {
    private final CategoryRepository categoryRepository;

    public CategoryBusinessRules(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void checkIfCategoryExists(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category", id);
        }
    }
    
    public void checkIfCategoryNameExists(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new BusinessException("Category with this name already exists: " + name);
        }
    }
    
    public void checkIfCategoryNameExistsForUpdate(Long categoryId, String name) {
        if (categoryRepository.existsByNameAndIdNot(name, categoryId)) {
            throw new BusinessException("Category with this name already exists: " + name);
        }
    }
    
    public void checkIfCategoryHasProducts(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category", categoryId));
        
        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            throw new BusinessException("Cannot delete category with products. Remove or reassign products first.");
        }
    }
    
    public void validateCategoryInformation(Category category) {
        Map<String, String> validationErrors = new HashMap<>();
        
        if (category.getName() == null || category.getName().isEmpty()) {
            validationErrors.put("name", "Category name cannot be empty");
        }
        
        if (!validationErrors.isEmpty()) {
            throw new ValidationException("Category validation failed", validationErrors);
        }
    }
}
