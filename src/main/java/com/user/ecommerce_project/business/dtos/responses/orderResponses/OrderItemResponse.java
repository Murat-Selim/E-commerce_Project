package com.user.ecommerce_project.business.dtos.responses.orderResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    
    private Long id;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    private Long productId;
    private String productName;
}
