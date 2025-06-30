package org.example.springsicurity2hw_2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.example.springsicurity2hw_2.entity.OurUsers;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignInResponse {
    private int statusCode;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String role;
    private String error;
    private OurUsers ourUsers;
}
