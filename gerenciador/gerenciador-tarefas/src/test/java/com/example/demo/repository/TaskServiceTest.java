package com.example.demo.repository;

import com.example.demo.domain.Priority;
import com.example.demo.domain.Status;
import com.example.demo.domain.Task;
import com.example.demo.service.TaskService;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
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

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        task = new Task("1", "Teste Titulo", "Teste descricao", Priority.MEDIUM, Status.DONE, LocalDate.now(), LocalDate.now());
    }

    @Test
    public void saveSuccess() {

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task taskTest = new Task("1", "Teste Titulo 1", "Teste descricao 1", Priority.HIGH, Status.DONE, LocalDate.now(), LocalDate.now());

        Task taskTestReturn = taskService.save(taskTest);

        assertEquals(taskTestReturn.getId(), "1");
        assertEquals(taskTestReturn.getStatus(), taskTest.getStatus());
        assertEquals(taskTestReturn.getTitle(), taskTest.getTitle());
        assertEquals(taskTestReturn.getDescription(), taskTest.getDescription());
        assertEquals(taskTestReturn.getDateConclusion(), taskTest.getDateConclusion());
        assertEquals(taskTestReturn.getPriority(), taskTest.getPriority());
    }

    @Test
    public void findAllSuccess() {

        List<Task> tasks = List.of(task, new Task("2", "Teste Titulo 2", "Teste descricao 2", Priority.MEDIUM, Status.TO_DO, LocalDate.now(), LocalDate.now()));
        when(taskRepository.findAll()).thenReturn(tasks);
        assertEquals(taskService.findAll().get(0).getId(), tasks.get(0).getId());
        assertEquals(taskService.findAll().get(1).getId(), tasks.get(1).getId());
    }

    @Test
    public void findByIdSuccess() {

        when(taskRepository.findById("1")).thenReturn(Optional.ofNullable(task));

        assertEquals(taskService.findById("1").get().getId(), task.getId());
    }

    @Test
    public void updateSuccess() {
        when(taskRepository.findById("1")).thenReturn(Optional.ofNullable(task));
        when(taskRepository.save(task)).thenReturn(new Task("1", "Teste UPDATE", "Teste descricao UPDATE", Priority.LOW, Status.TO_DO, LocalDate.of(1,1,1), LocalDate.of(1,1,1)));

        Task taskUpdate = taskService.update(task);

        assertEquals(taskUpdate.getId(), "1");
        assertEquals(taskUpdate.getTitle(), "Teste UPDATE");
        assertEquals(taskUpdate.getDescription(), "Teste descricao UPDATE");
        assertEquals(taskUpdate.getPriority(), Priority.LOW);
        assertEquals(taskUpdate.getStatus(), Status.TO_DO);
        assertEquals(taskUpdate.getDateStart(), LocalDate.of(1,1,1));
        assertEquals(taskUpdate.getDateConclusion(), LocalDate.of(1,1,1));

    }

    @Test
    public void deleteByIdSuccess() {

        taskService = mock(TaskService.class);

        when(taskRepository.deleteById("1")).thenCallRealMethod();

        verify(taskService, times(1)).deleteById("1");


    }
}
