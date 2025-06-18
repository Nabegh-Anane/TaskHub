package com.taskhub.taskhub_backend.services;

import com.taskhub.taskhub_backend.models.Epic;
import com.taskhub.taskhub_backend.models.Sprint;
import com.taskhub.taskhub_backend.repositories.EpicRepository;
import com.taskhub.taskhub_backend.repositories.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SprintService {

    private final SprintRepository sprintRepository;
    private final EpicRepository epicRepository;

    public Sprint createSprint(Sprint sprint, Long epicId) {
        Epic epic = epicRepository.findById(epicId).orElseThrow(() -> new RuntimeException("Epic not found"));
        sprint.setEpic(epic);
        return sprintRepository.save(sprint);
    }

    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    public List<Sprint> getSprintsByEpic(Long epicId) {
        return sprintRepository.findByEpicId(epicId);
    }

    public Optional<Sprint> getSprint(Long id) {
        return sprintRepository.findById(id);
    }

    public Sprint updateSprint(Long id, Sprint updatedSprint) {
        return sprintRepository.findById(id).map(sprint -> {
            sprint.setName(updatedSprint.getName());
            sprint.setDeadline(updatedSprint.getDeadline());
            return sprintRepository.save(sprint);
        }).orElseThrow(() -> new RuntimeException("Sprint not found"));
    }

    public void deleteSprint(Long id) {
        sprintRepository.deleteById(id);
    }
}
