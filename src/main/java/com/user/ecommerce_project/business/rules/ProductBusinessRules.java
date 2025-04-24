package com.user.ecommerce_project.business.rules;

import com.user.ecommerce_project.entities.Product;
import com.user.ecommerce_project.repositories.CategoryRepository;
import com.user.ecommerce_project.repositories.ProductRepository;
import com.user.ecommerce_project.utils.exceptions.BusinessException;
import com.user.ecommerce_project.utils.exceptions.NotFoundException;
import com.user.ecommerce_project.utils.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductBusinessRules {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductBusinessRules(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void checkIfProductExists(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product", id);
        }
    }
    
    public void checkIfCategoryExists(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Category", categoryId);
        }
    }
    
    public void checkIfStockAvailable(Long productId, int requestedQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product", productId));
        
        if (product.getStockQuantity() < requestedQuantity) {
            throw new BusinessException("Not enough stock for product: " + product.getName() + 
                    ". Available: " + product.getStockQuantity() + ", Requested: " + requestedQuantity);
        }
    }
    
    public void checkIfProductNameExists(String name) {
        if (productRepository.existsByName(name)) {
            throw new BusinessException("Product with this name already exists: " + name);
        }
    }
    
    public void checkIfProductNameExistsForUpdate(Long productId, String name) {
        if (productRepository.existsByNameAndIdNot(name, productId)) {
            throw new BusinessException("Product with this name already exists: " + name);
        }
    }
    
    public void validateProductInformation(Product product) {
        Map<String, String> validationErrors = new HashMap<>();
        
        if (product.getName() == null || product.getName().isEmpty()) {
            validationErrors.put("name", "Product name cannot be empty");
        }
        
        if (product.getPrice() == null) {
            validationErrors.put("price", "Product price must be greater than zero");
        }
        
        if (product.getStockQuantity() == null || product.getStockQuantity() < 0) {
            validationErrors.put("stockQuantity", "Product stock cannot be negative");
        }
        
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            validationErrors.put("categoryId", "Product must belong to a category");
        }
        
        if (!validationErrors.isEmpty()) {
            throw new ValidationException("Product validation failed", validationErrors);
        }
    }
}
