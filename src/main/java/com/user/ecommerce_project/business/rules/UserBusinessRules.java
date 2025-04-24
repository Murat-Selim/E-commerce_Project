package com.user.ecommerce_project.business.rules;

import com.user.ecommerce_project.entities.User;
import com.user.ecommerce_project.repositories.UserRepository;
import com.user.ecommerce_project.utils.exceptions.BusinessException;
import com.user.ecommerce_project.utils.exceptions.NotFoundException;
import com.user.ecommerce_project.utils.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserBusinessRules {
    private final UserRepository userRepository;

    public UserBusinessRules(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkIfUserExists(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User", id);
        }
    }

    public void checkIfEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("Email already in use: " + email);
        }
    }
    
    public void checkIfEmailExistsForUpdate(Long userId, String email) {
        if (userRepository.existsByEmailAndIdNot(email, userId)) {
            throw new BusinessException("Email already in use: " + email);
        }
    }
    
    public void checkIfEmailNotExists(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new NotFoundException("User", "email", email);
        }
    }
    
    public void validatePassword(String password, String confirmPassword) {
        Map<String, String> validationErrors = new HashMap<>();
        
        if (!password.equals(confirmPassword)) {
            validationErrors.put("confirmPassword", "Passwords do not match");
        }
        
        if (password.length() < 6) {
            validationErrors.put("password", "Password must be at least 6 characters");
        }
        
        if (!validationErrors.isEmpty()) {
            throw new ValidationException("Password validation failed", validationErrors);
        }
    }
    
    public void validateUserInformation(User user) {
        Map<String, String> validationErrors = new HashMap<>();
        
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            validationErrors.put("email", "Email cannot be empty");
        }
        
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            validationErrors.put("password", "Password must be at least 6 characters");
        }
        
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            validationErrors.put("firstName", "First name cannot be empty");
        }
        
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            validationErrors.put("lastName", "Last name cannot be empty");
        }
        
        if (!validationErrors.isEmpty()) {
            throw new ValidationException("User validation failed", validationErrors);
        }
    }
}
