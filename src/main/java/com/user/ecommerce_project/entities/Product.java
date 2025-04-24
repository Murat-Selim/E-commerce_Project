package com.user.ecommerce_project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(name = "name")
    String name;
    
    @Column(name = "description", length = 1000)
    String description;
    
    @Column(name = "price")
    BigDecimal price;
    
    @Column(name = "stock_quantity")
    Integer stockQuantity;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
    
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;
}
