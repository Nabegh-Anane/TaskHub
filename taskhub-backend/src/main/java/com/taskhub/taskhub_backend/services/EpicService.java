package com.taskhub.taskhub_backend.services;

import com.taskhub.taskhub_backend.models.Epic;
import com.taskhub.taskhub_backend.models.Project;
import com.taskhub.taskhub_backend.repositories.EpicRepository;
import com.taskhub.taskhub_backend.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EpicService {

    private final EpicRepository epicRepository;
    private final ProjectRepository projectRepository;

    public Epic createEpic(Epic epic, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        epic.setProject(project);
        return epicRepository.save(epic);
    }

    public List<Epic> getAllEpics() {
        return epicRepository.findAll();
    }

    public List<Epic> getEpicsByProject(Long projectId) {
        return epicRepository.findByProjectId(projectId);
    }

    public Optional<Epic> getEpic(Long id) {
        return epicRepository.findById(id);
    }

    public Epic updateEpic(Long id, Epic updatedEpic) {
        return epicRepository.findById(id).map(epic -> {
            epic.setName(updatedEpic.getName());
            epic.setDeadline(updatedEpic.getDeadline());
            return epicRepository.save(epic);
        }).orElseThrow(() -> new RuntimeException("Epic not found"));
    }

    public void deleteEpic(Long id) {
        epicRepository.deleteById(id);
    }
}
