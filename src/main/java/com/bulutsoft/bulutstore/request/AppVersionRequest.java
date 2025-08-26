package com.bulutsoft.bulutstore.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppVersionRequest {
    @NotNull
    private Long appId;
    @NotBlank
    private String version;
    @NotBlank
    private String apkPath;

    public Long getAppId() { return appId; }
    public void setAppId(Long appId) { this.appId = appId; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getApkPath() { return apkPath; }
    public void setApkPath(String apkPath) { this.apkPath = apkPath; }
}

