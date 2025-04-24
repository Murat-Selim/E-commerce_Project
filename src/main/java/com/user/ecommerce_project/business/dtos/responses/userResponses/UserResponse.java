package com.user.ecommerce_project.business.dtos.responses.userResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
