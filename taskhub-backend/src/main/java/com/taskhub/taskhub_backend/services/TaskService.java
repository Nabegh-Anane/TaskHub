package com.taskhub.taskhub_backend.services;

import com.taskhub.taskhub_backend.models.Task;
import com.taskhub.taskhub_backend.models.User;
import com.taskhub.taskhub_backend.models.Sprint;
import com.taskhub.taskhub_backend.repositories.TaskRepository;
import com.taskhub.taskhub_backend.repositories.UserRepository;
import com.taskhub.taskhub_backend.repositories.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;
    private final UserRepository userRepository;

    public Task createTask(Task task, Long sprintId, Long assignedToId) {
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(() -> new RuntimeException("Sprint not found"));
        User assignedTo = userRepository.findById(assignedToId).orElseThrow(() -> new RuntimeException("User not found"));
        task.setSprint(sprint);
        task.setAssignedTo(assignedTo);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksBySprint(Long sprintId) {
        return taskRepository.findBySprintId(sprintId);
    }

    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByAssignedToId(userId);
    }

    public Optional<Task> getTask(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setState(updatedTask.getState());
            task.setDeadline(updatedTask.getDeadline());
            if ("CLOSED".equalsIgnoreCase(updatedTask.getState())) {
                task.setClosedAt(LocalDateTime.now());
            } else {
                task.setClosedAt(null);
            }
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
