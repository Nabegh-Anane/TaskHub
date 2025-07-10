package com.taskhub.taskhub_backend.repositories;

import com.taskhub.taskhub_backend.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findBySprintId(Long sprintId);
    List<Task> findByAssignedToId(Long userId);
}
