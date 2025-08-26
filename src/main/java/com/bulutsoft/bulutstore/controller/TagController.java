package com.bulutsoft.bulutstore.controller;

import com.bulutsoft.bulutstore.dto.TagDto;
import com.bulutsoft.bulutstore.service.TagService;
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
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @Operation(summary = "Get all tags")
    @ApiResponse(responseCode = "200", description = "List of tags")
    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @Operation(summary = "Get tag by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tag found"),
        @ApiResponse(responseCode = "404", description = "Tag not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new tag")
    @ApiResponse(responseCode = "201", description = "Tag created")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TagDto> createTag(@Valid @RequestBody TagDto tagDto) {
        TagDto created = tagService.createTag(tagDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Update tag")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tag updated"),
        @ApiResponse(responseCode = "404", description = "Tag not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TagDto> updateTag(@PathVariable Long id, @Valid @RequestBody TagDto tagDto) {
        TagDto updated = tagService.updateTag(id, tagDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete tag")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Tag deleted"),
        @ApiResponse(responseCode = "404", description = "Tag not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}

