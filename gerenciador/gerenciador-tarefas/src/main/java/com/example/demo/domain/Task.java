package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Task {

    public Task(String title, String description, Priority priority, Status status, LocalDate dateStart, LocalDate dateConclusion) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dateStart = dateStart;
        this.dateConclusion = dateConclusion;
    }

    private String id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDate dateStart;
    private LocalDate dateConclusion;

}