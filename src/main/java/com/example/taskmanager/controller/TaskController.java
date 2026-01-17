package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskCreateRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.dto.TaskUpdateRequest;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.TaskStatus;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //READ ALL, GET /task
    @GetMapping
    public Page<TaskResponse> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable)
                .map(t -> new TaskResponse(
                        t.getId(),
                        t.getTitle(),
                        t.getStatus(),
                        t.getCreatedAt()
                ));
    }

    //CREATE, POST /task
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody TaskCreateRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setStatus(request.getStatus() == null ? TaskStatus.TODO : request.getStatus());

        Task saved = taskRepository.save(task);
        return new TaskResponse(saved.getId(), saved.getTitle(), saved.getStatus(), saved.getCreatedAt());
    }

    //READ ONE
    @GetMapping("/{id}")
    public TaskResponse getOne(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return new TaskResponse(task.getId(), task.getTitle(), task.getStatus(), task.getCreatedAt());
    }


    //UPDATE,
    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest request) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() ->
                new TaskNotFoundException(id));

        existingTask.setTitle(request.getTitle());
        existingTask.setStatus(request.getStatus());

        Task saved = taskRepository.save(existingTask);

        return new TaskResponse(saved.getId(), saved.getTitle(), saved.getStatus(), saved.getCreatedAt());
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(task);
    }
}
