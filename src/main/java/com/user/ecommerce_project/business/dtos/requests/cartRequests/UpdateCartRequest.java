package com.user.ecommerce_project.business.dtos.requests.cartRequests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartRequest {

    @DecimalMin(value = "0.0", inclusive = true, message = "Total price must be zero or positive")
    private BigDecimal totalPrice;

    @NotNull(message = "User ID is required")
    private Long userId;

    private List<UpdateCartItemRequest> cartItems;
}
