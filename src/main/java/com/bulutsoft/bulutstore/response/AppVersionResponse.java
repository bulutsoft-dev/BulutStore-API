package com.bulutsoft.bulutstore.response;

import java.time.LocalDateTime;

public class AppVersionResponse {
    private Long id;
    private Long appId;
    private String version;
    private String apkPath;
    private LocalDateTime releaseDate;

    public AppVersionResponse() {}

    public AppVersionResponse(Long id, Long appId, String version, String apkPath, LocalDateTime releaseDate) {
        this.id = id;
        this.appId = appId;
        this.version = version;
        this.apkPath = apkPath;
        this.releaseDate = releaseDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAppId() { return appId; }
    public void setAppId(Long appId) { this.appId = appId; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getApkPath() { return apkPath; }
    public void setApkPath(String apkPath) { this.apkPath = apkPath; }
    public LocalDateTime getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDateTime releaseDate) { this.releaseDate = releaseDate; }
}

