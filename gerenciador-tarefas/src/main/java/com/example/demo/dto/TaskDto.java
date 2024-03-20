package com.example.demo.dto;

import com.example.demo.domain.Priority;
import com.example.demo.domain.Status;
import com.example.demo.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private Priority priority;

    private Status status;

    private LocalDateTime dateCreate;

    private LocalDateTime dateConclusion;

    public TaskDto(String title, String description, Priority priority, Status status, LocalDateTime dateConclusion) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dateConclusion = dateConclusion;
    }

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.priority = task.getPriority();
        this.status = task.getStatus();
        this.dateCreate = task.getDateCreate();
        this.dateConclusion = task.getDateConclusion();
    }
}