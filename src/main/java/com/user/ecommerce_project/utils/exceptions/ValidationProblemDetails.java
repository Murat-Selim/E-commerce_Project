package com.user.ecommerce_project.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProblemDetails extends ProblemDetails {
    private Map<String, String> validationErrors;
    
    public ValidationProblemDetails(String title, String detail, int status, Map<String, String> validationErrors) {
        super(title, detail, status);
        this.validationErrors = validationErrors;
    }
}
