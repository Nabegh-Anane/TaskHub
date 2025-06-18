package com.taskhub.taskhub_backend.controllers;

import com.taskhub.taskhub_backend.models.Sprint;
import com.taskhub.taskhub_backend.services.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
public class SprintController {

    private final SprintService sprintService;

    @GetMapping
    public List<Sprint> getAllSprints() {
        return sprintService.getAllSprints();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sprint> getSprint(@PathVariable Long id) {
        return sprintService.getSprint(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/epic/{epicId}")
    public List<Sprint> getSprintsByEpic(@PathVariable Long epicId) {
        return sprintService.getSprintsByEpic(epicId);
    }

    @PostMapping("/epic/{epicId}")
    public Sprint createSprint(@RequestBody Sprint sprint, @PathVariable Long epicId) {
        return sprintService.createSprint(sprint, epicId);
    }

    @PutMapping("/{id}")
    public Sprint updateSprint(@PathVariable Long id, @RequestBody Sprint sprint) {
        return sprintService.updateSprint(id, sprint);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }
}
