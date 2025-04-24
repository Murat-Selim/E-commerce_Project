package com.user.ecommerce_project.business.dtos.responses.orderResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private String status;
    private String shippingAddress;
    private Long userId;
    private String userFullName;
}
