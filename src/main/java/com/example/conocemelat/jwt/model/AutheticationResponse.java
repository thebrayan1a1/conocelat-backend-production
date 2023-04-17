package com.example.conocemelat.jwt.model;

public class AutheticationResponse {

    private final String jwt;

    public AutheticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
