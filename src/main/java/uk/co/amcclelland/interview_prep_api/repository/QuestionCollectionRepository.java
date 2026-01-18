package uk.co.amcclelland.interview_prep_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.co.amcclelland.interview_prep_api.model.QuestionCollection;

@Repository
public interface QuestionCollectionRepository extends JpaRepository<QuestionCollection, Long> {
    boolean existsByNameIgnoreCase(String name);
}
