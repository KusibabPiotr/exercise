package com.cdq.exercise.repository;

import com.cdq.exercise.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
