package com.peacetry.TaskManagement.controller;


import com.peacetry.TaskManagement.model.dto.CreateTaskRequest;
import com.peacetry.TaskManagement.model.dto.TaskResponse;
import com.peacetry.TaskManagement.model.dto.UpdateTaskRequest;
import com.peacetry.TaskManagement.model.dto.UpdateTaskStatusRequest;
import com.peacetry.TaskManagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1.0/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody final CreateTaskRequest request) {

        final TaskResponse taskResponse = taskService.createTask(request);

        return ResponseEntity.status(201).body(taskResponse);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<TaskResponse>> createTasks(@RequestBody final List<CreateTaskRequest> requestList) {

       final List<TaskResponse> responses = requestList.stream()
                .map(taskService::createTask)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }


    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAllTasks() {

       final List<TaskResponse> tasks = taskService.findAllTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findTaskById(@PathVariable final UUID id) {

        final TaskResponse taskResponse = taskService.findTaskById(id);

        return ResponseEntity.ok(taskResponse);
    }
    @GetMapping("/status")
    public ResponseEntity<List<TaskResponse>> findTasksByCompletedStatus(@RequestParam final Boolean completed) {

        final List<TaskResponse> tasks = taskService.findTasksByCompletedStatus(completed);

        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable final UUID id, @Valid @RequestBody final UpdateTaskRequest request) {

        final TaskResponse updatedTask = taskService.updateTask(id, request);

        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable final  UUID id, @RequestBody @Valid final UpdateTaskStatusRequest updateTaskStatusRequest) {

        final TaskResponse updatedTask = taskService.updateTaskStatus(id, updateTaskStatusRequest);

        return ResponseEntity.ok(updatedTask);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable final UUID id) {

        taskService.deleteTaskById(id);

        return ResponseEntity.noContent().build();
    }
}
