package com.taskhub.taskhub_backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", schema = "taskhub")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String role;  // Exemple : "ROLE_SUPERADMIN", "ROLE_MANAGER", "ROLE_EMPLOYER"

    private boolean enabled;
}
