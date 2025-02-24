package com.peacetry.TaskManagement.service.Impl;

import com.peacetry.TaskManagement.exception.NotFoundException;
import com.peacetry.TaskManagement.mapper.TaskMapper;
import com.peacetry.TaskManagement.model.dto.CreateTaskRequest;
import com.peacetry.TaskManagement.model.dto.TaskResponse;
import com.peacetry.TaskManagement.model.dto.UpdateTaskRequest;
import com.peacetry.TaskManagement.model.dto.UpdateTaskStatusRequest;
import com.peacetry.TaskManagement.model.entity.Task;
import com.peacetry.TaskManagement.repository.TaskRepository;
import com.peacetry.TaskManagement.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponse createTask(final CreateTaskRequest createTaskRequest) {

        final Task task = taskMapper.toEntity(createTaskRequest) ;

        return taskMapper.toResponse(taskRepository.save(task));
    }

    @Override
    public List<TaskResponse> findAllTasks() {

        return taskRepository.findAll().stream()
                .map(taskMapper::toResponse)
                .toList();    }

    @Override
    public TaskResponse findTaskById(final UUID id) {

        final Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));

        return taskMapper.toResponse(task);    }

    @Override
    public TaskResponse updateTask(final UUID id, final UpdateTaskRequest updateTaskRequest) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));

        task = taskMapper.toEntity(updateTaskRequest, task);

        return taskMapper.toResponse(taskRepository.save(task));
    }


    @Override
    public void deleteTaskById(final UUID id) {

        final Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));


          taskRepository.delete(task);

    }

    @Override
    public TaskResponse updateTaskStatus(final UUID id, final UpdateTaskStatusRequest updateTaskStatusRequest) {

        final Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));

        task.setCompleted(updateTaskStatusRequest.completed());

        taskRepository.save(task);

        return taskMapper.toResponse(task);
    }

    @Override
    public List<TaskResponse> findTasksByCompletedStatus(final Boolean completed) {

        final List<Task> tasks = taskRepository.findByCompleted(completed);

       final  List<TaskResponse> responses = tasks.stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());

       return responses;
    }
}
