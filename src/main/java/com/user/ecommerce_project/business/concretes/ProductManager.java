package com.user.ecommerce_project.business.concretes;

import com.user.ecommerce_project.business.abstracts.ProductService;
import com.user.ecommerce_project.business.dtos.requests.productRequests.CreateProductRequest;
import com.user.ecommerce_project.business.dtos.requests.productRequests.UpdateProductRequest;
import com.user.ecommerce_project.business.dtos.requests.productRequests.UpdateProductStockRequest;
import com.user.ecommerce_project.business.dtos.responses.productResponses.ProductDetailResponse;
import com.user.ecommerce_project.business.dtos.responses.productResponses.ProductResponse;
import com.user.ecommerce_project.business.mapping.ECommerceMapper;
import com.user.ecommerce_project.business.rules.ProductBusinessRules;
import com.user.ecommerce_project.entities.Product;
import com.user.ecommerce_project.repositories.ProductRepository;
import com.user.ecommerce_project.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    
    private final ProductRepository productRepository;
    private final ECommerceMapper mapper;
    private final ProductBusinessRules rules;
    
    @Override
    public ProductResponse add(CreateProductRequest createProductRequest) {
        rules.checkIfCategoryExists(createProductRequest.getCategoryId());
        
        Product product = mapper.mapCreateProductRequestToProduct(createProductRequest);
        rules.validateProductInformation(product);
        productRepository.save(product);
        
        return mapper.mapProductToProductResponse(product);
    }
    
    @Override
    public ProductResponse update(Long id, UpdateProductRequest updateProductRequest) {
        rules.checkIfProductExists(id);
        
        if (updateProductRequest.getCategoryId() != null) {
            rules.checkIfCategoryExists(updateProductRequest.getCategoryId());
        }
        
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product", id));
        mapper.updateProductFromRequest(updateProductRequest, product);
        productRepository.save(product);
        
        return mapper.mapProductToProductResponse(product);
    }
    
    @Override
    public void delete(Long id) {
        rules.checkIfProductExists(id);
        productRepository.deleteById(id);
    }
    
    @Override
    public void updateStock(Long id, UpdateProductStockRequest updateProductStockRequest) {
        rules.checkIfProductExists(id);
        
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product", id));
        product.setStockQuantity(updateProductStockRequest.getStockQuantity());
        productRepository.save(product);
    }
    
    @Override
    public ProductResponse getById(Long id) {
        rules.checkIfProductExists(id);
        
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product", id));
        return mapper.mapProductToProductResponse(product);
    }
    
    @Override
    public ProductDetailResponse getDetailById(Long id) {
        rules.checkIfProductExists(id);
        
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product", id));
        return mapper.mapProductToProductDetailResponse(product);
    }
    
    @Override
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        return mapper.mapProductListToProductResponseList(products);
    }
    
    @Override
    public List<ProductResponse> getAllByCategoryId(Long categoryId) {
        rules.checkIfCategoryExists(categoryId);
        
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return mapper.mapProductListToProductResponseList(products);
    }
    
    @Override
    public List<ProductResponse> searchByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return mapper.mapProductListToProductResponseList(products);
    }
}
