package com.verycoolapp.idea.dto;

import jakarta.validation.constraints.NotBlank;

public record IdeaDTO(String id,
                      @NotBlank(message = "The title cannot be blank") String title,
                      @NotBlank(message = "The description cannot be blank") String description,
                      String group) {
}
