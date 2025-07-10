package com.taskhub.taskhub_backend.controllers;

import com.taskhub.taskhub_backend.models.Project;
import com.taskhub.taskhub_backend.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        return projectService.getProject(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/manager/{managerId}")
    public List<Project> getProjectsByManager(@PathVariable Long managerId) {
        return projectService.getProjectsByManager(managerId);
    }

    @PostMapping("/manager/{managerId}")
    public Project createProject(@RequestBody Project project, @PathVariable Long managerId) {
        return projectService.createProject(project, managerId);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
