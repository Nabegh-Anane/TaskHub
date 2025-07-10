package com.taskhub.taskhub_backend.services;

import com.taskhub.taskhub_backend.models.Comment;
import com.taskhub.taskhub_backend.models.Task;
import com.taskhub.taskhub_backend.models.User;
import com.taskhub.taskhub_backend.repositories.CommentRepository;
import com.taskhub.taskhub_backend.repositories.TaskRepository;
import com.taskhub.taskhub_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Comment addComment(Long taskId, Long userId, String content) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = Comment.builder()
                .content(content)
                .task(task)
                .author(user)
                .createdAt(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByTask(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }
}
