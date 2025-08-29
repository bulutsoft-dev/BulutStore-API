package com.bulutsoft.bulutstore.response;

import com.bulutsoft.bulutstore.entity.AppStatus;
import java.time.LocalDateTime;
import java.util.List;

public class AppResponse {
    private Long id;
    private String name;
    private String description;
    private String shortDescription;
    private CategoryResponse category;
    private DeveloperResponse developer;
    private AppStatus status;
    private long downloadCount;
    private Double avgRating;
    private List<TagResponse> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String iconUrl;
    private List<String> screenshotUrls;
    private String fileUrl;
    private String versionName;

    public AppResponse() {}

    public AppResponse(Long id, String name, String description, String shortDescription, CategoryResponse category, DeveloperResponse developer, AppStatus status, long downloadCount, Double avgRating, List<TagResponse> tags, LocalDateTime createdAt, LocalDateTime updatedAt, String iconUrl, List<String> screenshotUrls, String fileUrl, String versionName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.category = category;
        this.developer = developer;
        this.status = status;
        this.downloadCount = downloadCount;
        this.avgRating = avgRating;
        this.tags = tags;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.iconUrl = iconUrl;
        this.screenshotUrls = screenshotUrls;
        this.fileUrl = fileUrl;
        this.versionName = versionName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
    public CategoryResponse getCategory() { return category; }
    public void setCategory(CategoryResponse category) { this.category = category; }
    public DeveloperResponse getDeveloper() { return developer; }
    public void setDeveloper(DeveloperResponse developer) { this.developer = developer; }
    public AppStatus getStatus() { return status; }
    public void setStatus(AppStatus status) { this.status = status; }
    public long getDownloadCount() { return downloadCount; }
    public void setDownloadCount(long downloadCount) { this.downloadCount = downloadCount; }
    public Double getAvgRating() { return avgRating; }
    public void setAvgRating(Double avgRating) { this.avgRating = avgRating; }
    public List<TagResponse> getTags() { return tags; }
    public void setTags(List<TagResponse> tags) { this.tags = tags; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    public List<String> getScreenshotUrls() { return screenshotUrls; }
    public void setScreenshotUrls(List<String> screenshotUrls) { this.screenshotUrls = screenshotUrls; }
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public String getVersionName() { return versionName; }
    public void setVersionName(String versionName) { this.versionName = versionName; }
}
