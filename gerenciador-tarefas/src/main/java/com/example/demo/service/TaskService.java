package com.example.demo.service;

import com.example.demo.domain.Task;
import com.example.demo.dto.TaskDto;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskDto save(TaskDto task) {

        Task newTask = new Task(task);

        newTask = taskRepository.save(newTask);

        return new TaskDto(newTask);
    }

    private Task findByIdTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NullPointerException(String.format("Task with id = %d not found", id)));
    }

    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(TaskDto::new).toList();
    }

    public TaskDto findById(Long id) {
        return new TaskDto(findByIdTask(id));
    }

    public List<TaskDto> findAllTasksByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId, Sort.by("dateConclusion", "priority")).stream().map(TaskDto::new).toList();
    }

    public TaskDto update(TaskDto newTask) {

        Task oldTask = findByIdTask(newTask.getId());

        oldTask.update(newTask);

        oldTask = taskRepository.save(oldTask);

        return new TaskDto(oldTask);
    }

    public void deleteById(Long id) {

        Task task = this.findByIdTask(id);

        taskRepository.delete(task);
    }
}