package com.todolist.todolistproject.mapper;

import lombok.Data;

@Data
public class TaskStatusUpdateRequest {
    private Long id;
    private boolean completed;
}