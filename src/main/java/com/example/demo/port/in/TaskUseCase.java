package com.example.demo.port.in;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.interfaces.TaskFace;

public interface TaskUseCase<T extends TaskFace> {
    T createTask(String description);
    List<T> getAllTasks();
    T markCompleted(Long id);
    void deleteTask(Long id);
    List<T> getPendingTasks();
    Optional<T> getTaskById(Long id);
}
