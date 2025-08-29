package com.bulutsoft.bulutstore.response;

public class DownloadHistoryResponse {
    private long downloadCount;

    public DownloadHistoryResponse() {}
    public DownloadHistoryResponse(long downloadCount) {
        this.downloadCount = downloadCount;
    }
    public long getDownloadCount() { return downloadCount; }
    public void setDownloadCount(long downloadCount) { this.downloadCount = downloadCount; }
}
