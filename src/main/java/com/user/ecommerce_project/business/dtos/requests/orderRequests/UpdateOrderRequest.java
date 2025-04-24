package com.user.ecommerce_project.business.dtos.requests.orderRequests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    private LocalDateTime orderDate;

    @DecimalMin(value = "0.0", inclusive = true, message = "Total price must be zero or positive")
    private BigDecimal totalPrice;

    @Size(max = 50, message = "Status must be at most 50 characters")
    private String status;

    @Size(max = 255, message = "Shipping address must be at most 255 characters")
    private String shippingAddress;

    private List<UpdateOrderItemRequest> orderItems;
}