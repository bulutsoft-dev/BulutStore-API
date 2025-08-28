package com.bulutsoft.bulutstore.controller;

import com.bulutsoft.bulutstore.request.AppRequest;
import com.bulutsoft.bulutstore.response.AppResponse;
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
    public ResponseEntity<List<AppResponse>> getAllApps() {
        return ResponseEntity.ok(appService.getAllApps());
    }

    @Operation(summary = "Get app by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "App found"),
            @ApiResponse(responseCode = "404", description = "App not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AppResponse> getAppById(@PathVariable Long id) {
        return appService.getAppById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new app")
    @ApiResponse(responseCode = "201", description = "App created")
    @PostMapping
    @PreAuthorize("hasRole('DEVELOPER') or hasRole('ADMIN')")
    public ResponseEntity<AppResponse> createApp(@Valid @RequestBody AppRequest appRequest) {
        // Giriş yapan kullanıcının kimliğini al
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        AppResponse created = appService.createApp(appRequest, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Update app")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "App updated"),
            @ApiResponse(responseCode = "404", description = "App not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DEVELOPER') or hasRole('ADMIN')")
    public ResponseEntity<AppResponse> updateApp(@PathVariable Long id, @Valid @RequestBody AppRequest appRequest) {
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        AppResponse updated = appService.updateApp(id, appRequest, username);
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
    public ResponseEntity<List<AppResponse>> getAppsByDeveloper(@PathVariable Long developerId) {
        return ResponseEntity.ok(appService.getAppsByDeveloper(developerId));
    }

    @Operation(summary = "Get apps by category id")
    @ApiResponse(responseCode = "200", description = "List of apps by category")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<AppResponse>> getAppsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(appService.getAppsByCategory(categoryId));
    }

    @Operation(summary = "Search apps by name")
    @ApiResponse(responseCode = "200", description = "List of apps matching name")
    @GetMapping("/search")
    public ResponseEntity<List<AppResponse>> searchAppsByName(@RequestParam String name) {
        return ResponseEntity.ok(appService.searchAppsByName(name));
    }

    @Operation(summary = "Approve app (admin only)")
    @ApiResponse(responseCode = "200", description = "App approved")
    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> approveApp(@PathVariable Long id) {
        appService.approveApp(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Reject app (admin only)")
    @ApiResponse(responseCode = "200", description = "App rejected")
    @PostMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> rejectApp(@PathVariable Long id) {
        appService.rejectApp(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all approved apps (store)")
    @ApiResponse(responseCode = "200", description = "List of approved apps")
    @GetMapping("/approved")
    public ResponseEntity<List<AppResponse>> getApprovedApps() {
        return ResponseEntity.ok(appService.getApprovedApps());
    }
}
