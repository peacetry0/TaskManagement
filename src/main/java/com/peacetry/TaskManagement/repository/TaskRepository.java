package com.peacetry.TaskManagement.repository;

import com.peacetry.TaskManagement.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByCompleted(Boolean completed);
}
