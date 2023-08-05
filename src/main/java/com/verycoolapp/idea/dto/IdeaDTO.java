package com.verycoolapp.idea.dto;

import java.time.Instant;

public record IdeaDTO(String id,
                      String title,
                      String description,
                      String group,
                      String createdBy,
                      Instant createdDate,
                      String lastModifiedBy,
                      Instant lastModifiedDate) {
}
