package com.bulutsoft.bulutstore.controller;

import com.bulutsoft.bulutstore.request.UserCreateRequest;
import com.bulutsoft.bulutstore.request.UserDeveloperApplicationRequest;
import com.bulutsoft.bulutstore.request.UserUpdateRequest;
import com.bulutsoft.bulutstore.response.UserResponse;
import com.bulutsoft.bulutstore.service.UserService;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "List of users")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Get user by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "User created")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserResponse created = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Update user")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User updated"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
        UserResponse updated = userService.updateUser(id, request);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete user")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "User deleted"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Kullanıcı developer olmak için başvuru yapar")
    @PostMapping("/apply-developer")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> applyForDeveloper(@RequestBody @jakarta.validation.Valid UserDeveloperApplicationRequest request) {
        userService.applyForDeveloper(request.getApplicationText());
        return ResponseEntity.ok("Developer başvurusu alındı.");
    }

    @Operation(summary = "Admin: Developer başvurularını listeler")
    @GetMapping("/developer-applications")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getDeveloperApplications() {
        return ResponseEntity.ok(userService.getPendingDeveloperApplications());
    }

    @Operation(summary = "Admin: Developer başvurusunu onaylar")
    @PostMapping("/approve-developer/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> approveDeveloper(@PathVariable Long userId) {
        userService.approveDeveloper(userId);
        return ResponseEntity.ok("Developer başvurusu onaylandı.");
    }

    @Operation(summary = "Admin: Developer başvurusunu reddeder")
    @PostMapping("/reject-developer/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> rejectDeveloper(@PathVariable Long userId) {
        userService.rejectDeveloper(userId);
        return ResponseEntity.ok("Developer başvurusu reddedildi.");
    }

    @Operation(summary = "Get current user info")
    @ApiResponse(responseCode = "200", description = "Current user info")
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> getCurrentUserInfo(org.springframework.security.core.Authentication authentication) {
        String username = authentication.getName();
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Kullanıcı kendi developer başvurusunu görüntüler")
    @GetMapping("/developer-application/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<com.bulutsoft.bulutstore.response.DeveloperApplicationResponse> getOwnDeveloperApplication(org.springframework.security.core.Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(userService.getOwnDeveloperApplication(username));
    }
}
