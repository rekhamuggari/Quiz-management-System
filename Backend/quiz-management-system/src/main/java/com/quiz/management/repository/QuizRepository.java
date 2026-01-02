package com.quiz.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.management.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
