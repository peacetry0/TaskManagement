package com.peacetry.TaskManagement.mapper;


import com.peacetry.TaskManagement.model.dto.CreateTaskRequest;
import com.peacetry.TaskManagement.model.dto.TaskResponse;
import com.peacetry.TaskManagement.model.dto.UpdateTaskRequest;
import com.peacetry.TaskManagement.model.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(CreateTaskRequest request) {
        return new Task(
                request.title(),
                request.description(),
                false
        );
    }

    public Task toEntity(UpdateTaskRequest request, Task task) {
        task.setTitle(request.title());
        task.setDescription(request.description());
        return task;
    }

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getTitle(),
                task.getDescription(),
                task.getCompleted()

        );
    }

}
