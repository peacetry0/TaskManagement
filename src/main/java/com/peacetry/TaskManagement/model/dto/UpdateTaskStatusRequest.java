package com.peacetry.TaskManagement.model.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateTaskStatusRequest(


        @NotNull
        Boolean completed
) {
}
