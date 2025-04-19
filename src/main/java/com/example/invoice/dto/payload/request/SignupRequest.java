package com.example.invoice.dto.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;


    @Size(max = 255)
    private String userFirstName;

    @Size(max = 255)
    private String userLastName;


    @NotNull
    @Size(max = 255)
    @Email
    private String email;
    
    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

//    @Size(max = 255)
//    private String confirmPassword;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }

    public void setRole(Set<String> role) {
      this.role = role;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }
}
