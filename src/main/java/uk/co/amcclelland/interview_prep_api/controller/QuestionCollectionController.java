package uk.co.amcclelland.interview_prep_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uk.co.amcclelland.interview_prep_api.dto.collection.CollectionResponse;
import uk.co.amcclelland.interview_prep_api.dto.collection.CreateCollectionRequest;
import uk.co.amcclelland.interview_prep_api.model.QuestionCollection;
import uk.co.amcclelland.interview_prep_api.repository.QuestionCollectionRepository;
import java.util.List;

import java.net.URI;

@RestController
@RequestMapping("/collections")
public class QuestionCollectionController {

    private final QuestionCollectionRepository questionCollectionRepository;

    public QuestionCollectionController(QuestionCollectionRepository questionCollectionRepository) {
        this.questionCollectionRepository = questionCollectionRepository;
    }

    @PostMapping
    public ResponseEntity<CollectionResponse> createCollection(@Valid @RequestBody CreateCollectionRequest request) {
        // Basic guard against duplicates (your DB also enforces unique name)
        if (questionCollectionRepository.existsByNameIgnoreCase(request.name())) {
            return ResponseEntity.badRequest().build();
        }

        QuestionCollection collection = new QuestionCollection(request.name(), request.description());
        QuestionCollection saved = questionCollectionRepository.save(collection);

        CollectionResponse response = new CollectionResponse(saved.getId(), saved.getName(), saved.getDescription());

        // Location header: /collections/{id}
        URI location = URI.create("/collections/" + saved.getId());
        return ResponseEntity.created(location).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<CollectionResponse>> getAllCollections() {
        List<CollectionResponse> collections = questionCollectionRepository.findAll()
                .stream()
                .map(c -> new CollectionResponse(c.getId(), c.getName(), c.getDescription()))
                .toList();

        return ResponseEntity.ok(collections);
    }
}
