package com.cdq.exercise.service;

import com.cdq.exercise.repository.TaskRepository;
import com.cdq.exercise.dto.TaskCreateRequest;
import com.cdq.exercise.dto.TaskDto;
import com.cdq.exercise.entity.Status;
import com.cdq.exercise.entity.Task;
import com.cdq.exercise.exception.TaskNotFoundException;
import com.cdq.exercise.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    public static final Integer FULL_PROCENTAGE_PROGRESS = 100;
    private static final Integer PART_PROCENTAGE_PROGRESS = 25;

    @Async("async")
    public CompletableFuture<Long> createTask(final TaskCreateRequest request) {
        Task task = TaskMapper.mapRequestToTask(request);
        task.setProgress(0);
        task.setStatus(Status.RUNNING);
        Task savedTask = taskRepository.saveAndFlush(task);
        try {
            processTask(savedTask);
        } catch (InterruptedException e) {
            log.warn(Arrays.toString(e.getStackTrace()));
        }
        return CompletableFuture.completedFuture(savedTask.getId());
    }

    private void processTask(final Task savedTask) throws InterruptedException {
        Integer progress = savedTask.getProgress();
        while (!progress.equals(FULL_PROCENTAGE_PROGRESS)) {
            savedTask.setProgress(progress + PART_PROCENTAGE_PROGRESS);
            taskRepository.saveAndFlush(savedTask);

            progress += PART_PROCENTAGE_PROGRESS;
            Thread.sleep(2000);
        }
        savedTask.setStatus(Status.FINISHED);
        savedTask.setResult((long) Math.pow(savedTask.getBase(), savedTask.getExponent()));
        taskRepository.saveAndFlush(savedTask);
    }

    public TaskDto getTask(final Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
        return TaskMapper.mapTaskToDto(task);
    }

    public List<TaskDto> getTasks() {
        return TaskMapper.mapToTaskDtoList(taskRepository.findAll());
    }
}
