package com.example.demo.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Task;
import com.example.demo.port.in.TaskUseCase;
import com.example.demo.port.out.TaskRepositoryPort;



@Service
public class TaskService implements TaskUseCase<Task>{

    private final TaskRepositoryPort<Task> taskRepository;

    public TaskService(TaskRepositoryPort<Task> taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
}

    @Override
    public Task createTask(String description) {
        Task task = new Task(null, description, false);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task markCompleted(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task no encontrada"));
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getPendingTasks() {
        return taskRepository.findPending();
    }
}
