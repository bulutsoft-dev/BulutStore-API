package com.bulutsoft.bulutstore.controller;

import com.bulutsoft.bulutstore.request.DownloadHistoryRequest;
import com.bulutsoft.bulutstore.response.DownloadHistoryResponse;
import com.bulutsoft.bulutstore.service.DownloadHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/download-histories")
@RequiredArgsConstructor
public class DownloadHistoryController {
    private final DownloadHistoryService downloadHistoryService;

    @Operation(summary = "Create new download history")
    @ApiResponse(responseCode = "201", description = "Download history created")
    @PostMapping
    public ResponseEntity<DownloadHistoryResponse> createDownloadHistory(@Valid @RequestBody DownloadHistoryRequest request) {
        DownloadHistoryResponse created = downloadHistoryService.createDownloadHistory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Get download count by app id")
    @ApiResponse(responseCode = "200", description = "Download count for app")
    @GetMapping("/count/{appId}")
    public ResponseEntity<DownloadHistoryResponse> getDownloadCountByAppId(@PathVariable Long appId) {
        return ResponseEntity.ok(downloadHistoryService.getDownloadCountByAppId(appId));
    }
}
