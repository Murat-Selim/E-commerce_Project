package com.user.ecommerce_project.business.dtos.requests.cartRequests;

import com.user.ecommerce_project.business.dtos.requests.cartRequests.AddCartItemRequest;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCartRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @DecimalMin(value = "0.0", inclusive = true, message = "Total price must be zero or positive")
    private BigDecimal totalPrice;

    @NotNull(message = "Cart items are required")
    @Size(min = 1, message = "At least one cart item is required")
    private List<AddCartItemRequest> cartItems;
}