package com.taskhub.taskhub_backend.repositories;

import com.taskhub.taskhub_backend.models.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
    List<Sprint> findByEpicId(Long epicId);
}
