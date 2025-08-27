package com.bulutsoft.bulutstore.response;

import com.bulutsoft.bulutstore.entity.AppStatus;
import java.time.LocalDateTime;
import java.util.List;

public class AppResponse {
    private Long id;
    private String name;
    private String description;
    private String shortDescription;
    private Long categoryId;
    private Long developerId;
    private AppStatus status;
    private Long downloadsCount;
    private Double avgRating;
    private List<Long> tagIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String iconUrl;
    private List<String> screenshotUrls;
    private String developerDisplayName;
    private String developerWebsite;

    public AppResponse() {}

    public AppResponse(Long id, String name, String description, String shortDescription, Long categoryId, Long developerId, AppStatus status, Long downloadsCount, Double avgRating, List<Long> tagIds, LocalDateTime createdAt, LocalDateTime updatedAt, String iconUrl, List<String> screenshotUrls, String developerDisplayName, String developerWebsite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.categoryId = categoryId;
        this.developerId = developerId;
        this.status = status;
        this.downloadsCount = downloadsCount;
        this.avgRating = avgRating;
        this.tagIds = tagIds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.iconUrl = iconUrl;
        this.screenshotUrls = screenshotUrls;
        this.developerDisplayName = developerDisplayName;
        this.developerWebsite = developerWebsite;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
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
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    public List<String> getScreenshotUrls() { return screenshotUrls; }
    public void setScreenshotUrls(List<String> screenshotUrls) { this.screenshotUrls = screenshotUrls; }
    public String getDeveloperDisplayName() { return developerDisplayName; }
    public void setDeveloperDisplayName(String developerDisplayName) { this.developerDisplayName = developerDisplayName; }
    public String getDeveloperWebsite() { return developerWebsite; }
    public void setDeveloperWebsite(String developerWebsite) { this.developerWebsite = developerWebsite; }
}
