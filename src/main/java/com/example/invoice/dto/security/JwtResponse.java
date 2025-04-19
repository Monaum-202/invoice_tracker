package com.example.invoice.dto.security;


import com.example.invoice.entity.security.Users;

public class JwtResponse {

    private Users users;
    private String jwtToken;

    public JwtResponse(Users users, String jwtToken) {
        this.users = users;
        this.jwtToken = jwtToken;
    }

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }


}
