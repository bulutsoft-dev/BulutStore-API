package com.bulutsoft.bulutstore.controller;

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
    public ResponseEntity<List<DownloadHistoryDto>> getAllDownloadHistories() {
        return ResponseEntity.ok(downloadHistoryService.getAllDownloadHistories());
    }

    @Operation(summary = "Get download history by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Download history found"),
        @ApiResponse(responseCode = "404", description = "Download history not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DownloadHistoryDto> getDownloadHistoryById(@PathVariable Long id) {
        return downloadHistoryService.getDownloadHistoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new download history")
    @ApiResponse(responseCode = "201", description = "Download history created")
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DownloadHistoryDto> createDownloadHistory(@Valid @RequestBody DownloadHistoryDto downloadHistoryDto) {
        DownloadHistoryDto created = downloadHistoryService.createDownloadHistory(downloadHistoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Update download history")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Download history updated"),
        @ApiResponse(responseCode = "404", description = "Download history not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DownloadHistoryDto> updateDownloadHistory(@PathVariable Long id, @Valid @RequestBody DownloadHistoryDto downloadHistoryDto) {
        DownloadHistoryDto updated = downloadHistoryService.updateDownloadHistory(id, downloadHistoryDto);
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

