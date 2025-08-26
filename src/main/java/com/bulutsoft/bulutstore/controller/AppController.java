package com.bulutsoft.bulutstore.controller;

import com.bulutsoft.bulutstore.dto.AppDto;
import com.bulutsoft.bulutstore.service.AppService;
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
@RequestMapping("/api/apps")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @Operation(summary = "Get all apps")
    @ApiResponse(responseCode = "200", description = "List of apps")
    @GetMapping
    public ResponseEntity<List<AppDto>> getAllApps() {
        return ResponseEntity.ok(appService.getAllApps());
    }

    @Operation(summary = "Get app by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "App found"),
            @ApiResponse(responseCode = "404", description = "App not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AppDto> getAppById(@PathVariable Long id) {
        return appService.getAppById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new app")
    @ApiResponse(responseCode = "201", description = "App created")
    @PostMapping
    @PreAuthorize("hasRole('DEVELOPER') or hasRole('ADMIN')")
    public ResponseEntity<AppDto> createApp(@Valid @RequestBody AppDto appDto) {
        AppDto created = appService.createApp(appDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Update app")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "App updated"),
            @ApiResponse(responseCode = "404", description = "App not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DEVELOPER') or hasRole('ADMIN')")
    public ResponseEntity<AppDto> updateApp(@PathVariable Long id, @Valid @RequestBody AppDto appDto) {
        AppDto updated = appService.updateApp(id, appDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete app")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "App deleted"),
            @ApiResponse(responseCode = "404", description = "App not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteApp(@PathVariable Long id) {
        appService.deleteApp(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get apps by developer id")
    @ApiResponse(responseCode = "200", description = "List of apps by developer")
    @GetMapping("/developer/{developerId}")
    public ResponseEntity<List<AppDto>> getAppsByDeveloper(@PathVariable Long developerId) {
        return ResponseEntity.ok(appService.getAppsByDeveloper(developerId));
    }

    @Operation(summary = "Get apps by category id")
    @ApiResponse(responseCode = "200", description = "List of apps by category")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<AppDto>> getAppsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(appService.getAppsByCategory(categoryId));
    }

    @Operation(summary = "Search apps by name")
    @ApiResponse(responseCode = "200", description = "List of apps matching name")
    @GetMapping("/search")
    public ResponseEntity<List<AppDto>> searchAppsByName(@RequestParam String name) {
        return ResponseEntity.ok(appService.searchAppsByName(name));
    }
}
