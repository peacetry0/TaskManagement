package com.peacetry.TaskManagement.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String title,
        String description,
        Boolean completed) {
}
