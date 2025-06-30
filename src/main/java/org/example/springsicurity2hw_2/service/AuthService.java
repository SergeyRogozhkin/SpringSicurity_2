package org.example.springsicurity2hw_2.service;


import org.example.springsicurity2hw_2.dto.*;
import org.example.springsicurity2hw_2.entity.OurUsers;
import org.example.springsicurity2hw_2.repository.OurUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private OurUserRepository ourUserRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public SignUpResponse signUp(SignUpRequest request) {
        SignUpResponse response = new SignUpResponse();
        try {
            OurUsers ourUser = new OurUsers();
            ourUser.setEmail(request.getEmail());
            ourUser.setPassword(passwordEncoder.encode(request.getPassword()));
            ourUser.setRole(request.getRole());
            OurUsers ourUsersResult = ourUserRepository.save(ourUser);
            if (ourUsersResult != null && ourUsersResult.getId() > 0) {
                response.setOurUsers(ourUsersResult);
                response.setMessage("User has been registered successfully");
                response.setStatusCode(200);
            }

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public SignInResponse signIn(SignInRequest request) {
        SignInResponse response = new SignInResponse();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = ourUserRepository.findByEmail(request.getEmail()).orElseThrow();
            System.out.println("User is " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("1hr");
            response.setMessage("successfully signed in");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshRequests) {
        RefreshTokenResponse response = new RefreshTokenResponse();
        String ourEmail = jwtUtils.extractUsername(refreshRequests.getToken());
        OurUsers users = ourUserRepository.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshRequests.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshRequests.getToken());
            response.setExpirationTime("1hr");
            response.setMessage("successfully refreshed token");
        }
        response.setStatusCode(500);
        return response;


    }
}