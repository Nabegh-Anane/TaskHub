package com.taskhub.taskhub_backend.services;

import com.taskhub.taskhub_backend.models.Project;
import com.taskhub.taskhub_backend.models.User;
import com.taskhub.taskhub_backend.repositories.ProjectRepository;
import com.taskhub.taskhub_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Project createProject(Project project, Long managerId) {
        User manager = userRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager not found"));
        project.setManager(manager);
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByManager(Long managerId) {
        return projectRepository.findByManagerId(managerId);
    }

    public Optional<Project> getProject(Long id) {
        return projectRepository.findById(id);
    }

    public Project updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id).map(project -> {
            project.setName(updatedProject.getName());
            project.setDescription(updatedProject.getDescription());
            project.setDeadline(updatedProject.getDeadline());
            return projectRepository.save(project);
        }).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
