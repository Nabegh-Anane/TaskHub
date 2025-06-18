package com.taskhub.taskhub_backend.controllers;

import com.taskhub.taskhub_backend.models.Notification;
import com.taskhub.taskhub_backend.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PreAuthorize("hasAnyRole('SUPERADMIN', 'MANAGER', 'EMPLOYER')")
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotifications(userId));
    }

    @PreAuthorize("hasAnyRole('SUPERADMIN', 'MANAGER', 'EMPLOYER')")
    @PostMapping("/user/{userId}")
    public Notification createNotification(@PathVariable Long userId, @RequestBody String message) {
        return notificationService.createNotification(userId, message);
    }

    @PreAuthorize("hasAnyRole('SUPERADMIN', 'MANAGER', 'EMPLOYER')")
    @PutMapping("/{notificationId}/read")
    public Notification markAsRead(@PathVariable Long notificationId) {
        return notificationService.markAsRead(notificationId);
    }
}
