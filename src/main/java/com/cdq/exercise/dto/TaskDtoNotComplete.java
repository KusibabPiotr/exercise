package com.cdq.exercise.dto;

import com.cdq.exercise.entity.Status;
import lombok.Data;

@Data
public class TaskDtoNotComplete extends TaskDto {
    public TaskDtoNotComplete(final Long id, final Status status, final String progress) {
        super(id, status, progress);
    }
}
