package uk.co.amcclelland.interview_prep_api.dto.question;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateQuestionRequest(
                @NotBlank(message = "Title is required") @Size(max = 255, message = "Title must be 255 characters or fewer") String title) {
}
