package com.peacetry.TaskManagement.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(


        @NotBlank
        String title,
        @NotBlank
        @Size(min = 10)
        String description) {

}
