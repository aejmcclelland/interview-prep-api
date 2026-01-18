package uk.co.amcclelland.interview_prep_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import uk.co.amcclelland.interview_prep_api.model.QuestionCollection;
import uk.co.amcclelland.interview_prep_api.repository.QuestionCollectionRepository;
import uk.co.amcclelland.interview_prep_api.repository.QuestionRepository;
import uk.co.amcclelland.interview_prep_api.model.Question;

@SpringBootApplication
public class InterviewPrepApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewPrepApiApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner seedData(QuestionCollectionRepository questionCollectionRepository,
			QuestionRepository questionRepository) {
		return args -> {
			// Only seed if empty
			if (questionCollectionRepository.count() > 0 || questionRepository.count() > 0)
				return;
			QuestionCollection arrays = new QuestionCollection(
					"Arrays",
					"Starter questions for array patterns");
			QuestionCollection strings = new QuestionCollection(
					"Strings",
					"Starter questions for string patterns");
			questionCollectionRepository.save(arrays);
			questionCollectionRepository.save(strings);
			questionRepository.save(new Question("Contains Duplicate", arrays));
			questionRepository.save(new Question("Two Sum", arrays));
			questionRepository.save(new Question("Valid Anagram", strings));
		};
	}

}
