package com.example.demo.domain;

import com.example.demo.dto.TaskDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Tasks")
public class Task {

    public Task(String title, String description, Priority priority, Status status, LocalDateTime dateConclusion) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dateCreate = LocalDateTime.now();
        this.dateConclusion = dateConclusion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String title;

    private String description;

    private Priority priority;

    private Status status;

    private LocalDateTime dateCreate;

    private LocalDateTime dateConclusion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(TaskDto task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.priority = task.getPriority();
        this.status = task.getStatus();
        this.dateCreate = task.getDateCreate() == null ? LocalDateTime.now() : task.getDateCreate();
        this.dateConclusion = task.getDateConclusion();
    }

    public void update(TaskDto newTask) {
        this.title = newTask.getTitle();
        this.dateConclusion = newTask.getDateConclusion();
        this.description = newTask.getDescription();
        this.status = newTask.getStatus();
        this.priority = newTask.getPriority();
    }
}