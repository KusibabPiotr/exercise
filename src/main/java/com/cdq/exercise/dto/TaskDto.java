package com.cdq.exercise.dto;

import com.cdq.exercise.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private Status status;
    private String progress;
}
