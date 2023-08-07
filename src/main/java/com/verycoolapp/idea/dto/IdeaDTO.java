package com.verycoolapp.idea.dto;

import jakarta.validation.constraints.NotBlank;

public record IdeaDTO(String id,
                      @NotBlank String title,
                      @NotBlank String description,
                      String group) {
}
