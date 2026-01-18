package uk.co.amcclelland.interview_prep_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uk.co.amcclelland.interview_prep_api.dto.question.CreateQuestionRequest;
import uk.co.amcclelland.interview_prep_api.dto.question.QuestionResponse;
import uk.co.amcclelland.interview_prep_api.model.Question;
import uk.co.amcclelland.interview_prep_api.model.QuestionCollection;
import uk.co.amcclelland.interview_prep_api.repository.QuestionCollectionRepository;
import uk.co.amcclelland.interview_prep_api.repository.QuestionRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/collections/{collectionId}/questions")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final QuestionCollectionRepository questionCollectionRepository;

    public QuestionController(QuestionRepository questionRepository,
            QuestionCollectionRepository questionCollectionRepository) {
        this.questionRepository = questionRepository;
        this.questionCollectionRepository = questionCollectionRepository;
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(
            @PathVariable Long collectionId,
            @Valid @RequestBody CreateQuestionRequest request) {
        QuestionCollection collection = questionCollectionRepository.findById(collectionId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Collection not found: " + collectionId));

        Question question = new Question(request.title(), collection);
        Question saved = questionRepository.save(question);

        QuestionResponse response = new QuestionResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getQuestionCollection().getId());

        URI location = URI.create("/collections/" + collectionId + "/questions/" + saved.getId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getQuestionsForCollection(@PathVariable Long collectionId) {

        if (!questionCollectionRepository.existsById(collectionId)) {
            return ResponseEntity.notFound().build();
        }

        List<QuestionResponse> responses = questionRepository.findByQuestionCollectionId(collectionId)
                .stream()
                .map(q -> new QuestionResponse(q.getId(), q.getTitle(), collectionId))
                .toList();

        return ResponseEntity.ok(responses);
    }
}
