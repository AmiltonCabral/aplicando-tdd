package com.example.demo.service;

import com.example.demo.domain.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Optional<Task> findById(String id){
        return taskRepository.findById(id);
    }

    public Task update(Task task) {
        Task oldTask = taskRepository.findById(task.getId()).get();
        oldTask.setDescription(task.getDescription());
        oldTask.setStatus(task.getStatus());
        oldTask.setTitle(task.getTitle());
        oldTask.setDateConclusion(task.getDateConclusion());
        oldTask.setDateStart(task.getDateStart());
        oldTask.setPriority(task.getPriority());

        return taskRepository.save(oldTask);
    }

    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }

}
