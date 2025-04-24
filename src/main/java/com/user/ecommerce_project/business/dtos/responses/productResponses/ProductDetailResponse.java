package com.user.ecommerce_project.business.dtos.responses.productResponses;

import com.user.ecommerce_project.business.dtos.responses.categoryResponses.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private boolean inStock;
    private CategoryResponse category;
}
