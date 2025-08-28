package com.bulutsoft.bulutstore.response;

import java.time.LocalDateTime;

public class ReviewResponse {
    private Long id;
    private Long appId;
    private String username;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    public ReviewResponse() {}

    public ReviewResponse(Long id, Long appId, String username, int rating, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.appId = appId;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAppId() { return appId; }
    public void setAppId(Long appId) { this.appId = appId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
