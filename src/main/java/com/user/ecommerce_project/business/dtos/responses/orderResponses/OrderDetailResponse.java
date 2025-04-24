package com.user.ecommerce_project.business.dtos.responses.orderResponses;

import com.user.ecommerce_project.business.dtos.responses.userResponses.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {
    
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private String status;
    private String shippingAddress;
    private UserResponse user;
    private List<OrderItemResponse> orderItems;
}
