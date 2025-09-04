package com.example.demo.application;

import com.example.demo.domain.Task;
import com.example.demo.port.out.TaskRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepositoryPort<Task> repository;

    @InjectMocks
    private TaskService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        Task task = new Task(null, "Aprender Spring Boot", false);
        when(repository.save(any(Task.class))).thenReturn(new Task(1L, task.getDescription(), false));

        Task result = service.createTask(task.getDescription());
        assertNotNull(result.getId());
        assertEquals("Aprender Spring Boot", result.getDescription());
        assertFalse(result.isCompleted());
    }

    @Test
    void testMarkCompleted() {
        Task task = new Task(1L, "Tarea", false);
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        when(repository.save(task)).thenReturn(new Task(1L, "Tarea", true));

        Task result = service.markCompleted(1L);
        assertTrue(result.isCompleted());
    }

    @Test
    void testGetPendingTasks() {
        Task t1 = new Task(1L, "T1", false);
        Task t2 = new Task(2L, "T2", true);
        when(repository.findPending()).thenReturn(List.of(t1));

        List<Task> pending = service.getPendingTasks();
        assertEquals(1, pending.size());
        assertFalse(pending.get(0).isCompleted());
    }
}
