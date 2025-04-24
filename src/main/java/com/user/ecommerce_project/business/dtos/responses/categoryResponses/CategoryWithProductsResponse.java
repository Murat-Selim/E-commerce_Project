package com.user.ecommerce_project.business.dtos.responses.categoryResponses;

import com.user.ecommerce_project.business.dtos.responses.productResponses.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWithProductsResponse {
    
    private Long id;
    private String name;
    private String description;
    private List<ProductResponse> products;
}
