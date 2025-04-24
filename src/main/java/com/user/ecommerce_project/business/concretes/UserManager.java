package com.user.ecommerce_project.business.concretes;

import com.user.ecommerce_project.business.abstracts.UserService;
import com.user.ecommerce_project.business.dtos.requests.userRequests.CreateUserRequest;
import com.user.ecommerce_project.business.dtos.requests.userRequests.UpdateUserPasswordRequest;
import com.user.ecommerce_project.business.dtos.requests.userRequests.UpdateUserRequest;
import com.user.ecommerce_project.business.dtos.responses.userResponses.UserResponse;
import com.user.ecommerce_project.business.mapping.ECommerceMapper;
import com.user.ecommerce_project.business.rules.UserBusinessRules;
import com.user.ecommerce_project.entities.User;
import com.user.ecommerce_project.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    
    private final UserRepository userRepository;
    private final ECommerceMapper mapper;
    private final UserBusinessRules rules;
    
    @Override
    public UserResponse add(CreateUserRequest createUserRequest) {
        rules.checkIfEmailExists(createUserRequest.getEmail());
        
        User user = mapper.mapCreateUserRequestToUser(createUserRequest);
        rules.validateUserInformation(user);
        userRepository.save(user);
        
        return mapper.mapUserToUserResponse(user);
    }
    
    @Override
    public UserResponse update(Long id, UpdateUserRequest updateUserRequest) {
        rules.checkIfUserExists(id);
        
        if (updateUserRequest.getEmail() != null) {
            rules.checkIfEmailExistsForUpdate(id, updateUserRequest.getEmail());
        }
        
        User user = userRepository.findById(id).orElseThrow();
        mapper.updateUserFromRequest(updateUserRequest, user);
        userRepository.save(user);
        
        return mapper.mapUserToUserResponse(user);
    }
    
    @Override
    public void updatePassword(Long id, UpdateUserPasswordRequest updateUserPasswordRequest) {
        rules.checkIfUserExists(id);
        
        rules.validatePassword(
            updateUserPasswordRequest.getNewPassword(), 
            updateUserPasswordRequest.getConfirmPassword()
        );
        
        User user = userRepository.findById(id).orElseThrow();
        user.setPassword(updateUserPasswordRequest.getNewPassword());
        userRepository.save(user);
    }
    
    @Override
    public void delete(Long id) {
        rules.checkIfUserExists(id);
        userRepository.deleteById(id);
    }
    
    @Override
    public UserResponse getById(Long id) {
        rules.checkIfUserExists(id);
        
        User user = userRepository.findById(id).orElseThrow();
        return mapper.mapUserToUserResponse(user);
    }
    
    @Override
    public UserResponse getByEmail(String email) {
        rules.checkIfEmailNotExists(email);
        
        User user = userRepository.findByEmail(email).orElseThrow();
        return mapper.mapUserToUserResponse(user);
    }
    
    @Override
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return mapper.mapUserListToUserResponseList(users);
    }
}
