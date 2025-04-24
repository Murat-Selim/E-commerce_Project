package com.user.ecommerce_project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "cart_items")
public class CartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(name = "quantity")
    Integer quantity;
    
    @Column(name = "unit_price")
    BigDecimal unitPrice;
    
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
