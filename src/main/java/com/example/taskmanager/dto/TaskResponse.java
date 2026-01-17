package com.example.taskmanager.dto;

import com.example.taskmanager.entity.TaskStatus;
import java.time.Instant;


public class TaskResponse {
    private Long id;
    private String title;
    private TaskStatus status;
    private Instant createdAt;

    public TaskResponse(Long id, String title, TaskStatus status, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public TaskStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }





}
