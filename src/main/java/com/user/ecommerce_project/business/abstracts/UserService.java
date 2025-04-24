package com.user.ecommerce_project.business.abstracts;

import com.user.ecommerce_project.business.dtos.requests.userRequests.CreateUserRequest;
import com.user.ecommerce_project.business.dtos.requests.userRequests.UpdateUserPasswordRequest;
import com.user.ecommerce_project.business.dtos.requests.userRequests.UpdateUserRequest;
import com.user.ecommerce_project.business.dtos.responses.userResponses.UserResponse;

import java.util.List;

public interface UserService {
    
    UserResponse add(CreateUserRequest createUserRequest);
    
    UserResponse update(Long id, UpdateUserRequest updateUserRequest);
    
    void updatePassword(Long id, UpdateUserPasswordRequest updateUserPasswordRequest);
    
    void delete(Long id);
    
    UserResponse getById(Long id);
    
    UserResponse getByEmail(String email);
    
    List<UserResponse> getAll();
}
