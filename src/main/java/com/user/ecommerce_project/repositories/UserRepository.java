package com.user.ecommerce_project.repositories;

import com.user.ecommerce_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    boolean existsByEmailAndIdNot(String email, Long id);
    
    Optional<User> findByEmailAndPassword(String email, String password);
    
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
    
    List<User> findByLastNameContainingIgnoreCase(String lastName);
}
