package com.cdq.exercise.controller;

import com.cdq.exercise.dto.TaskCreateRequest;
import com.cdq.exercise.dto.TaskDto;
import com.cdq.exercise.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Long> createTask(@RequestBody TaskCreateRequest request)
            throws InterruptedException, ExecutionException {
        return ResponseEntity.ok(taskService.createTask(request).get());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }
}
