package com.cdq.exercise.dto;

import com.cdq.exercise.entity.Status;
import lombok.Data;

@Data
public class TaskDtoComplete extends TaskDto {
    private Long result;

    public TaskDtoComplete(final Long id, final Status status, final String progress, final Long result) {
        super(id, status, progress);
        this.result = result;
    }
}
