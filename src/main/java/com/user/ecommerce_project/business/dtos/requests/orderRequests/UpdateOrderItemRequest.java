package com.user.ecommerce_project.business.dtos.requests.orderRequests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderItemRequest {

    @NotNull(message = "Order item ID is required")
    private Long id;

    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @DecimalMin(value = "0.0", inclusive = true, message = "Unit price must be zero or positive")
    private BigDecimal unitPrice;

    private Long orderId;
}
