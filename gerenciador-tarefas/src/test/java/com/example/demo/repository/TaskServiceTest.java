package com.example.demo.repository;

import com.example.demo.domain.Priority;
import com.example.demo.domain.Status;
import com.example.demo.domain.Task;
import com.example.demo.dto.TaskDto;
import com.example.demo.service.TaskService;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @InjectMocks
    private static TaskService taskService;

    @Mock
    private static TaskRepository taskRepository;

    private static Task task;

    private static TaskDto taskDto;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        task = new Task("Teste Titulo", "Teste descricao", Priority.MEDIUM, Status.DONE, LocalDateTime.now());

        taskDto = new TaskDto("Teste Titulo 1", "Teste descricao 1", Priority.LOW, Status.TO_DO, LocalDateTime.now());
        taskDto.setId(2L);
    }

    @Test
    public void saveSuccess() {

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskDto taskTest = new TaskDto("Teste Titulo", "Teste descricao", Priority.MEDIUM, Status.DONE, LocalDateTime.now());

        TaskDto taskTestReturn = taskService.save(taskTest);

        assertEquals(taskTestReturn.getStatus(), taskTest.getStatus());
        assertEquals(taskTestReturn.getTitle(), taskTest.getTitle());
        assertEquals(taskTestReturn.getDescription(), taskTest.getDescription());
        assertEquals(taskTestReturn.getDateConclusion().getDayOfYear(), taskTest.getDateConclusion().getDayOfYear());
        assertEquals(taskTestReturn.getDateConclusion().getDayOfMonth(), taskTest.getDateConclusion().getDayOfMonth());
        assertEquals(taskTestReturn.getDateConclusion().getHour(), taskTest.getDateConclusion().getHour());
        assertEquals(taskTestReturn.getDateConclusion().getMinute(), taskTest.getDateConclusion().getMinute());
        assertEquals(taskTestReturn.getPriority(), taskTest.getPriority());
    }

    @Test
    public void findAllSuccess() {

        List<Task> tasks = List.of(task, new Task("Teste Titulo 2", "Teste descricao 2", Priority.MEDIUM, Status.TO_DO, LocalDateTime.now()));
        when(taskRepository.findAll()).thenReturn(tasks);
        assertEquals(taskService.findAll().get(0).getId(), tasks.get(0).getId());
        assertEquals(taskService.findAll().get(1).getId(), tasks.get(1).getId());
    }

    @Test
    public void findByIdSuccess() {

        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(task));

        assertEquals(taskService.findById(1L).getId(), task.getId());
    }

    @Test
    public void updateSuccess() {
        when(taskRepository.findById(2L)).thenReturn(Optional.ofNullable(task));
        when(taskRepository.save(task)).thenReturn(new Task("Teste UPDATE", "Teste descricao UPDATE", Priority.LOW, Status.TO_DO, LocalDateTime.of(1, 1, 1, 1, 1, 1, 1)));

        TaskDto taskUpdate = taskService.update(taskDto);

        assertEquals(taskUpdate.getTitle(), "Teste UPDATE");
        assertEquals(taskUpdate.getDescription(), "Teste descricao UPDATE");
        assertEquals(taskUpdate.getPriority(), Priority.LOW);
        assertEquals(taskUpdate.getStatus(), Status.TO_DO);
        assertEquals(taskUpdate.getDateConclusion(), LocalDateTime.of(1, 1, 1, 1, 1, 1, 1));

    }

    @Test
    public void deleteByIdSuccess() {


    }
}
