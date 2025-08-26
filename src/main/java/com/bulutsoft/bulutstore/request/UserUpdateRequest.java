package com.bulutsoft.bulutstore.request;

import com.bulutsoft.bulutstore.entity.Role;
import com.bulutsoft.bulutstore.entity.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserUpdateRequest {
    @Size(max = 50)
    private String username;

    @Email
    @Size(max = 100)
    private String email;

    private String password;

    private Role role;
    private UserStatus status;

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public UserStatus getStatus() { return status; }
    public void setStatus(UserStatus status) { this.status = status; }
}

