package com.example.demo.functionalTests;

import com.example.demo.domain.Priority;
import com.example.demo.dto.TaskDto;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class ValueLimitTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void createTaskWithDueDateBeforeCurrentDate() {
        TaskDto newTaskDto = new TaskDto();
        newTaskDto.setTitle("Test Task");
        newTaskDto.setDateCreate(LocalDateTime.now().minusDays(1));
        newTaskDto.setPriority(Priority.HIGH);


        assertThrows(Exception.class, () -> taskService.save(newTaskDto));
    }

    @Test
    public void createTaskWithDueDateAfterCurrentDate() {

        TaskDto newTaskDto = new TaskDto();
        newTaskDto.setTitle("Test Task");
        newTaskDto.setDateCreate(LocalDateTime.now().plusDays(1));
        newTaskDto.setPriority(Priority.HIGH);

        TaskDto savedTaskDto = taskService.save(newTaskDto);

        assertNotNull(savedTaskDto);
    }

    @Test
    public void createTaskWithDueDateAfterOneYear() {

        TaskDto newTaskDto = new TaskDto();
        newTaskDto.setTitle("Test Task");
        newTaskDto.setDateCreate(LocalDateTime.now().plusYears(1).plusDays(1));
        newTaskDto.setPriority(Priority.HIGH);

        assertThrows(Exception.class, () -> taskService.save(newTaskDto));
    }

    @Test
    public void updateTaskWithDueDateBeforeCurrentDate() {

        TaskDto existingTaskDto = new TaskDto();
        existingTaskDto.setId(1L);
        existingTaskDto.setTitle("Existing Task");
        existingTaskDto.setDateCreate(LocalDateTime.now().minusDays(1));
        existingTaskDto.setPriority(Priority.HIGH);

        // Execution and Verification
        assertThrows(Exception.class, () -> taskService.update(existingTaskDto));
    }

    @Test
    public void updateTaskWithDueDateAfterOneYear() {
        // Setup
        TaskDto existingTaskDto = new TaskDto();
        existingTaskDto.setId(1L);
        existingTaskDto.setTitle("Existing Task");
        existingTaskDto.setDateCreate(LocalDateTime.now().plusYears(1).plusDays(1));
        existingTaskDto.setPriority(Priority.HIGH);

        // Execution and Verification
        assertThrows(Exception.class, () -> taskService.update(existingTaskDto));
    }

    @Test
    public void updateTaskWithDueDateAfterCurrentDate() {
        // Setup
        TaskDto existingTaskDto = new TaskDto();
        existingTaskDto.setId(1L);
        existingTaskDto.setTitle("Existing Task");
        existingTaskDto.setDateCreate(LocalDateTime.now().plusDays(1));
        existingTaskDto.setPriority(Priority.HIGH);

        TaskDto updatedTaskDto = taskService.update(existingTaskDto);

        assertNotNull(updatedTaskDto);
    }
}
