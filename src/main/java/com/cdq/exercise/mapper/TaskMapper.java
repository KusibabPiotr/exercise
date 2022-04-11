package com.cdq.exercise.mapper;

import com.cdq.exercise.dto.TaskCreateRequest;
import com.cdq.exercise.dto.TaskDto;
import com.cdq.exercise.dto.TaskDtoComplete;
import com.cdq.exercise.dto.TaskDtoNotComplete;
import com.cdq.exercise.entity.Status;
import com.cdq.exercise.entity.Task;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TaskMapper {
    public static Task mapRequestToTask(final TaskCreateRequest request) {
        return Task.builder()
                .base(request.getBase())
                .exponent(request.getExponent())
                .build();
    }
    public static TaskDto mapTaskToDto(final Task task) {
        if(task.getStatus().equals(Status.FINISHED)){
            return new TaskDtoComplete(task.getId(), task.getStatus(),
                    String.valueOf(task.getProgress()).concat("%"), task.getResult());
        }
        return new TaskDtoNotComplete(task.getId(), task.getStatus(),
                String.valueOf(task.getProgress()).concat("%"));
    }
    public static List<TaskDto> mapToTaskDtoList(final List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::mapTaskToDto)
                .collect(toList());
    }
}
