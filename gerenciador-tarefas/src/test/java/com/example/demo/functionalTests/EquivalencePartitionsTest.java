package com.example.demo.functionalTests;

import com.example.demo.domain.Priority;
import com.example.demo.domain.Task;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquivalencePartitionsTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void saveNewTaskWithAllFieldsFilledCorrectly() {

        TaskDto newTaskDto = new TaskDto();
        newTaskDto.setTitle("Test Task");
        newTaskDto.setDateConclusion(LocalDateTime.now().plusDays(7));
        newTaskDto.setPriority(Priority.HIGH);

        Task savedTask = new Task(newTaskDto);
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);


        TaskDto savedTaskDto = taskService.save(newTaskDto);

        assertNotNull(savedTaskDto);
        assertEquals(newTaskDto.getTitle(), savedTaskDto.getTitle());
        assertEquals(newTaskDto.getDateConclusion(), savedTaskDto.getDateConclusion());
        assertEquals(newTaskDto.getPriority(), savedTaskDto.getPriority());
    }

    @Test
    public void attemptToSaveNewTaskWithRequiredFieldsNotFilled() {

        TaskDto newTaskDto = new TaskDto();


        assertThrows(Exception.class, () -> taskService.save(newTaskDto));
        verify(taskRepository, never()).save(any());
    }

    @Test
    public void createTaskWithMandatoryFieldsFilledCorrectly() {

        TaskDto newTaskDto = new TaskDto();
        newTaskDto.setTitle("Test Task");
        newTaskDto.setDescription("Task description");
        newTaskDto.setDateConclusion(LocalDateTime.now().plusDays(1));
        newTaskDto.setPriority(Priority.HIGH);

        TaskDto savedTaskDto = taskService.save(newTaskDto);

        assertNotNull(savedTaskDto);
    }

    @Test
    public void attemptCreateTaskWithMandatoryFieldsNotFilled() {

        TaskDto newTaskDto = new TaskDto();

        assertThrows(Exception.class, () -> taskService.save(newTaskDto));
    }

    @Test
    public void updateExistingTask() {

        TaskDto existingTaskDto = new TaskDto();
        existingTaskDto.setId(1L);
        existingTaskDto.setTitle("Existing Task");
        existingTaskDto.setDescription("Description of Existing Task");
        existingTaskDto.setDateConclusion(LocalDateTime.now().plusDays(1));
        existingTaskDto.setPriority(Priority.HIGH);

        TaskDto updatedTaskDto = taskService.update(existingTaskDto);

        assertNotNull(updatedTaskDto);
    }

    @Test
    public void attemptUpdateNonExistentTask() {
        TaskDto nonExistentTaskDto = new TaskDto();
        nonExistentTaskDto.setId(999L);

        assertThrows(Exception.class, () -> taskService.update(nonExistentTaskDto));
    }
}
