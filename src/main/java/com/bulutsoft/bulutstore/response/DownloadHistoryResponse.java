package com.bulutsoft.bulutstore.response;

import java.time.LocalDateTime;

public class DownloadHistoryResponse {
    private Long id;
    private Long appId;
    private Long userId;
    private LocalDateTime downloadedAt;

    public DownloadHistoryResponse() {}

    public DownloadHistoryResponse(Long id, Long appId, Long userId, LocalDateTime downloadedAt) {
        this.id = id;
        this.appId = appId;
        this.userId = userId;
        this.downloadedAt = downloadedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAppId() { return appId; }
    public void setAppId(Long appId) { this.appId = appId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public LocalDateTime getDownloadedAt() { return downloadedAt; }
    public void setDownloadedAt(LocalDateTime downloadedAt) { this.downloadedAt = downloadedAt; }
}

