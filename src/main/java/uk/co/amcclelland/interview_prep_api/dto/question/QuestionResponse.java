package uk.co.amcclelland.interview_prep_api.dto.question;

public record QuestionResponse (
    Long id,
    String title,
    Long questionCollectionId
) {
    
}
