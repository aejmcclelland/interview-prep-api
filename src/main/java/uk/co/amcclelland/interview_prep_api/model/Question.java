package uk.co.amcclelland.interview_prep_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_collection_id", nullable = false, foreignKey = @ForeignKey(name = "fk_questions_question_collection"))
    private QuestionCollection questionCollection;

    // JPA requires a no-args constructor
    protected Question() {
    }

    public Question(String title, QuestionCollection questionCollection) {
        this.title = title;
        this.questionCollection = questionCollection;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionCollection getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(QuestionCollection questionCollection) {
        this.questionCollection = questionCollection;
    }
}