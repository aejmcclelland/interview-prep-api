package uk.co.amcclelland.interview_prep_api.dto.collection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCollectionRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 120, message = "Name must be 120 characters or fewer")
        String name,

        @Size(max = 500, message = "Description must be 500 characters or fewer")
        String description
) {
}
