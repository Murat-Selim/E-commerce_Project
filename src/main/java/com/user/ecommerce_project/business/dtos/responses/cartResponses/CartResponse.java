package com.user.ecommerce_project.business.dtos.responses.cartResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    
    private Long id;
    private BigDecimal totalPrice;
    private Long userId;
    private List<CartItemResponse> cartItems;
    private int itemCount;
}
