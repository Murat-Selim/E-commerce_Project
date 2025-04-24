package com.user.ecommerce_project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(name = "first_name")
    String firstName;
    
    @Column(name = "last_name")
    String lastName;
    
    @Column(name = "email", unique = true)
    String email;
    
    @Column(name = "password")
    String password;
    
    @Column(name = "phone_number")
    String phoneNumber;
    
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    
    @OneToOne(mappedBy = "user")
    private Cart cart;
}
