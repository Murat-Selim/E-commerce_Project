package com.user.ecommerce_project.controllers;

import com.user.ecommerce_project.business.abstracts.UserService;
import com.user.ecommerce_project.business.dtos.requests.userRequests.CreateUserRequest;
import com.user.ecommerce_project.business.dtos.requests.userRequests.UpdateUserPasswordRequest;
import com.user.ecommerce_project.business.dtos.requests.userRequests.UpdateUserRequest;
import com.user.ecommerce_project.business.dtos.responses.userResponses.UserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping("/add")
    public ResponseEntity<UserResponse> add(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(userService.add(createUserRequest), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return new ResponseEntity<>(userService.update(id, updateUserRequest), HttpStatus.OK);
    }
    
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest) {
        userService.updatePassword(id, updateUserPasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }
    
    @GetMapping("/by-email")
    public ResponseEntity<UserResponse> getByEmail(@RequestParam String email) {
        return new ResponseEntity<>(userService.getByEmail(email), HttpStatus.OK);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponse>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}
