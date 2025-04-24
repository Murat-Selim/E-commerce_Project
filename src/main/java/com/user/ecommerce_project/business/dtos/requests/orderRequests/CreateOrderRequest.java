package com.user.ecommerce_project.business.dtos.requests.orderRequests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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
public class CreateOrderRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Order date is required")
    private LocalDateTime orderDate;

    @NotNull(message = "Total price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Total price must be zero or positive")
    private BigDecimal totalPrice;

    @NotBlank(message = "Status is required")
    @Size(max = 50, message = "Status must be at most 50 characters")
    private String status;

    @NotBlank(message = "Shipping address is required")
    @Size(max = 255, message = "Shipping address must be at most 255 characters")
    private String shippingAddress;

    @NotNull(message = "Order items are required")
    @Size(min = 1, message = "At least one order item is required")
    private List<CreateOrderItemRequest> orderItems;
}
