package org.example.springsicurity2hw_2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignInRequest {
    private String email;
    private String password;
    private String role;
}
