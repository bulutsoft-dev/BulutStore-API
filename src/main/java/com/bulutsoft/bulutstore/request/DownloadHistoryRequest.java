package com.bulutsoft.bulutstore.request;

import jakarta.validation.constraints.NotNull;

public class DownloadHistoryRequest {
    @NotNull
    private Long appId;
    private Long userId; // nullable for anonymous downloads

    public Long getAppId() { return appId; }
    public void setAppId(Long appId) { this.appId = appId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

