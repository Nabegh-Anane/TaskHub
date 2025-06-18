package com.taskhub.taskhub_backend.controllers;

import com.taskhub.taskhub_backend.models.Comment;
import com.taskhub.taskhub_backend.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PreAuthorize("hasAnyRole('SUPERADMIN', 'MANAGER', 'EMPLOYER')")
    @PostMapping("/task/{taskId}/user/{userId}")
    public Comment addComment(@PathVariable Long taskId, @PathVariable Long userId, @RequestBody String content) {
        return commentService.addComment(taskId, userId, content);
    }

    @PreAuthorize("hasAnyRole('SUPERADMIN', 'MANAGER', 'EMPLOYER')")
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Comment>> getCommentsByTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(commentService.getCommentsByTask(taskId));
    }
}
