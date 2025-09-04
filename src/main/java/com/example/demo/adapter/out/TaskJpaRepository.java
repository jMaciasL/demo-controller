package com.example.demo.adapter.out;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Task;

@Repository
public interface TaskJpaRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompletedFalse();
}

