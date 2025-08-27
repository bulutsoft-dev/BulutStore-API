package com.bulutsoft.bulutstore.request;

import com.bulutsoft.bulutstore.entity.AppStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class AppRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long developerId;
    private AppStatus status;
    private List<Long> tagIds;
    private String iconUrl;
    private List<String> screenshotUrls;
    private String shortDescription;

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
    public List<Long> getTagIds() { return tagIds; }
    public void setTagIds(List<Long> tagIds) { this.tagIds = tagIds; }
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    public List<String> getScreenshotUrls() { return screenshotUrls; }
    public void setScreenshotUrls(List<String> screenshotUrls) { this.screenshotUrls = screenshotUrls; }
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
}
