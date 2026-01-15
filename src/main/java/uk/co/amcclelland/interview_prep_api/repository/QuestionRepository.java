package uk.co.amcclelland.interview_prep_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.co.amcclelland.interview_prep_api.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}