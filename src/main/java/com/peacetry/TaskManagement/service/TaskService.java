package com.peacetry.TaskManagement.service;

import com.peacetry.TaskManagement.model.dto.CreateTaskRequest;
import com.peacetry.TaskManagement.model.dto.TaskResponse;
import com.peacetry.TaskManagement.model.dto.UpdateTaskRequest;
import com.peacetry.TaskManagement.model.dto.UpdateTaskStatusRequest;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponse createTask(CreateTaskRequest createTaskRequest);

    List<TaskResponse> findAllTasks();
    TaskResponse findTaskById(UUID id);
    TaskResponse updateTask(UUID id, UpdateTaskRequest updateTaskRequest);
    void deleteTaskById(UUID id);
    TaskResponse updateTaskStatus(UUID id, UpdateTaskStatusRequest updateTaskStatusRequest);
    List<TaskResponse> findTasksByCompletedStatus(Boolean completed);

}
