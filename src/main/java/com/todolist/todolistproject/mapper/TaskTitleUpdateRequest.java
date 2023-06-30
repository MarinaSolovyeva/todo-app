package com.todolist.todolistproject.mapper;

import lombok.Data;

@Data
public class TaskTitleUpdateRequest {
    private Long id;
    private String title;
}