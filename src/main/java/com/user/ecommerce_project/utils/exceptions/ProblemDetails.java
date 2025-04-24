package com.user.ecommerce_project.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDetails {
    private String title;
    private String detail;
    private int status;
    private String type;
    private String instance;
    private LocalDateTime timestamp;
    private Map<String, Object> properties;
    
    public ProblemDetails(String title, String detail, int status) {
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
