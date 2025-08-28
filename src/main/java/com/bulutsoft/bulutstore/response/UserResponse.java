package com.bulutsoft.bulutstore.response;

import com.bulutsoft.bulutstore.entity.Role;
import com.bulutsoft.bulutstore.entity.UserStatus;
import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String displayName;
    private String website;
    private String developerApplicationText;

    public UserResponse() {}

    public UserResponse(Long id, String username, String email, Role role, UserStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, String displayName, String website, String developerApplicationText) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.displayName = displayName;
        this.website = website;
        this.developerApplicationText = developerApplicationText;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public UserStatus getStatus() { return status; }
    public void setStatus(UserStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public String getDeveloperApplicationText() { return developerApplicationText; }
    public void setDeveloperApplicationText(String developerApplicationText) { this.developerApplicationText = developerApplicationText; }
}
