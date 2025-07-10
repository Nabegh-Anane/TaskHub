package com.taskhub.taskhub_backend.controllers;

import com.taskhub.taskhub_backend.models.Epic;
import com.taskhub.taskhub_backend.services.EpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epics")
@RequiredArgsConstructor
public class EpicController {

    private final EpicService epicService;

    @GetMapping
    public List<Epic> getAllEpics() {
        return epicService.getAllEpics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Epic> getEpic(@PathVariable Long id) {
        return epicService.getEpic(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/project/{projectId}")
    public List<Epic> getEpicsByProject(@PathVariable Long projectId) {
        return epicService.getEpicsByProject(projectId);
    }

    @PostMapping("/project/{projectId}")
    public Epic createEpic(@RequestBody Epic epic, @PathVariable Long projectId) {
        return epicService.createEpic(epic, projectId);
    }

    @PutMapping("/{id}")
    public Epic updateEpic(@PathVariable Long id, @RequestBody Epic epic) {
        return epicService.updateEpic(id, epic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpic(@PathVariable Long id) {
        epicService.deleteEpic(id);
        return ResponseEntity.noContent().build();
    }
}
