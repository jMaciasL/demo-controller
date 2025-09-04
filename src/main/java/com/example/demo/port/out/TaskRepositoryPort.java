package com.example.demo.port.out;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.interfaces.TaskFace;


public interface TaskRepositoryPort<T extends TaskFace> {
    T save(T task);
    Optional<T> findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
    List<T> findPending();
}
