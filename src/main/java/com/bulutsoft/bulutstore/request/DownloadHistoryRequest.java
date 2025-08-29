package com.bulutsoft.bulutstore.request;

import jakarta.validation.constraints.NotNull;

public class DownloadHistoryRequest {
    @NotNull
    private Long appId;

    public Long getAppId() { return appId; }
    public void setAppId(Long appId) { this.appId = appId; }
}
