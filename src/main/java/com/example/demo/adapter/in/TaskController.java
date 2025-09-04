package com.example.demo.adapter.in;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Task;
import com.example.demo.port.in.TaskUseCase;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskUseCase<Task> taskUseCase;

    public TaskController(TaskUseCase<Task> taskUseCase) {
        this.taskUseCase = taskUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return taskUseCase.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskUseCase.getAllTasks();
    }

    @GetMapping("/pending")
    public List<Task> getPendingTasks() {
        return taskUseCase.getPendingTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskUseCase.createTask(task.getDescription());
    }
    

    @PostMapping("/{id}/complete")
    public Task completeTask(@PathVariable Long id) {
        return taskUseCase.markCompleted(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskUseCase.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
