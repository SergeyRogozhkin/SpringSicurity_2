package org.example.springsicurity2hw_2.dto;

import lombok.Data;

@Data
public class RefreshTokenResponse {
    private int statusCode;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;

}