package com.taskhub.taskhub_backend.repositories;

import com.taskhub.taskhub_backend.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByManagerId(Long managerId);
}
