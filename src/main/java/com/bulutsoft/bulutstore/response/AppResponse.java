package com.bulutsoft.bulutstore.response;

import com.bulutsoft.bulutstore.entity.AppStatus;
import java.time.LocalDateTime;
import java.util.List;

public class AppResponse {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Long developerId;
    private AppStatus status;
    private Long downloadsCount;
    private Double avgRating;
    private List<Long> tagIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AppResponse() {}

    public AppResponse(Long id, String name, String description, Long categoryId, Long developerId, AppStatus status, Long downloadsCount, Double avgRating, List<Long> tagIds, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.developerId = developerId;
        this.status = status;
        this.downloadsCount = downloadsCount;
        this.avgRating = avgRating;
        this.tagIds = tagIds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public Long getDeveloperId() { return developerId; }
    public void setDeveloperId(Long developerId) { this.developerId = developerId; }
    public AppStatus getStatus() { return status; }
    public void setStatus(AppStatus status) { this.status = status; }
    public Long getDownloadsCount() { return downloadsCount; }
    public void setDownloadsCount(Long downloadsCount) { this.downloadsCount = downloadsCount; }
    public Double getAvgRating() { return avgRating; }
    public void setAvgRating(Double avgRating) { this.avgRating = avgRating; }
    public List<Long> getTagIds() { return tagIds; }
    public void setTagIds(List<Long> tagIds) { this.tagIds = tagIds; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

