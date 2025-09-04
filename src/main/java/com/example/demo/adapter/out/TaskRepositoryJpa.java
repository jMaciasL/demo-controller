package com.example.demo.adapter.out;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Task;
import com.example.demo.port.out.TaskRepositoryPort;

@Repository
public class TaskRepositoryJpa implements TaskRepositoryPort<Task> {

    private final TaskJpaRepository repository;

    public TaskRepositoryJpa(TaskJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task save(Task task) {
        return repository.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Task> findPending() {
        return repository.findByCompletedFalse();
    }
}
