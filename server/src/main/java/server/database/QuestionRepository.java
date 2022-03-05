package server.database;

import org.springframework.data.jpa.repository.JpaRepository;

import commons.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {}