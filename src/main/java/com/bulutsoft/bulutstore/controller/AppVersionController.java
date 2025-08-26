package com.bulutsoft.bulutstore.controller;

import com.bulutsoft.bulutstore.service.AppVersionService;
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
@RequestMapping("/api/app-versions")
@RequiredArgsConstructor
public class AppVersionController {
    private final AppVersionService appVersionService;

    @Operation(summary = "Get all app versions")
    @ApiResponse(responseCode = "200", description = "List of app versions")
    @GetMapping
    public ResponseEntity<List<AppVersionDto>> getAllAppVersions() {
        return ResponseEntity.ok(appVersionService.getAllAppVersions());
    }

    @Operation(summary = "Get app version by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "App version found"),
        @ApiResponse(responseCode = "404", description = "App version not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AppVersionDto> getAppVersionById(@PathVariable Long id) {
        return appVersionService.getAppVersionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new app version")
    @ApiResponse(responseCode = "201", description = "App version created")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AppVersionDto> createAppVersion(@Valid @RequestBody AppVersionDto appVersionDto) {
        AppVersionDto created = appVersionService.createAppVersion(appVersionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Update app version")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "App version updated"),
        @ApiResponse(responseCode = "404", description = "App version not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AppVersionDto> updateAppVersion(@PathVariable Long id, @Valid @RequestBody AppVersionDto appVersionDto) {
        AppVersionDto updated = appVersionService.updateAppVersion(id, appVersionDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete app version")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "App version deleted"),
        @ApiResponse(responseCode = "404", description = "App version not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAppVersion(@PathVariable Long id) {
        appVersionService.deleteAppVersion(id);
        return ResponseEntity.noContent().build();
    }
}
