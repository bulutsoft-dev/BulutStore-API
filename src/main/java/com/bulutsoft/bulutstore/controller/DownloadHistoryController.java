package com.bulutsoft.bulutstore.controller;

import com.bulutsoft.bulutstore.request.DownloadHistoryRequest;
import com.bulutsoft.bulutstore.response.DownloadHistoryResponse;
import com.bulutsoft.bulutstore.service.DownloadHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/download-histories")
@RequiredArgsConstructor
public class DownloadHistoryController {
    private final DownloadHistoryService downloadHistoryService;

    @Operation(summary = "Get all download histories")
    @ApiResponse(responseCode = "200", description = "List of download histories")
    @GetMapping
    public ResponseEntity<List<DownloadHistoryResponse>> getAllDownloadHistories() {
        return ResponseEntity.ok(downloadHistoryService.getAllDownloadHistories());
    }

    @Operation(summary = "Get download history by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Download history found"),
        @ApiResponse(responseCode = "404", description = "Download history not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DownloadHistoryResponse> getDownloadHistoryById(@PathVariable Long id) {
        return downloadHistoryService.getDownloadHistoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new download history")
    @ApiResponse(responseCode = "201", description = "Download history created")
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DownloadHistoryResponse> createDownloadHistory(@Valid @RequestBody DownloadHistoryRequest request) {
        DownloadHistoryResponse created = downloadHistoryService.createDownloadHistory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Update download history")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Download history updated"),
        @ApiResponse(responseCode = "404", description = "Download history not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DownloadHistoryResponse> updateDownloadHistory(@PathVariable Long id, @Valid @RequestBody DownloadHistoryRequest request) {
        DownloadHistoryResponse updated = downloadHistoryService.updateDownloadHistory(id, request);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete download history")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Download history deleted"),
        @ApiResponse(responseCode = "404", description = "Download history not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteDownloadHistory(@PathVariable Long id) {
        downloadHistoryService.deleteDownloadHistory(id);
        return ResponseEntity.noContent().build();
    }
}
