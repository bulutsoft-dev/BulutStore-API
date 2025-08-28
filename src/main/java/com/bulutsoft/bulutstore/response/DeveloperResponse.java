package com.bulutsoft.bulutstore.response;

public class DeveloperResponse {
    private Long id;
    private String username;
    private String displayName;
    private String website;
    private String email;

    public DeveloperResponse() {}

    public DeveloperResponse(Long id, String username, String displayName, String website, String email) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.website = website;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

