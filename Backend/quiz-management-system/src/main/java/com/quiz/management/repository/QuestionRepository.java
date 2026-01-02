package com.quiz.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.management.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
