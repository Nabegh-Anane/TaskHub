package com.taskhub.taskhub_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "epics", schema = "taskhub")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Epic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private LocalDateTime deadline;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "epic", cascade = CascadeType.ALL)
    private List<Sprint> sprints;
}
