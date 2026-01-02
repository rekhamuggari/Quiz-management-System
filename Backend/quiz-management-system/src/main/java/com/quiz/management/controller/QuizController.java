package com.quiz.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.management.dto.QuizSubmissionRequest;
import com.quiz.management.entity.Quiz;
import com.quiz.management.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuiz();
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("/{id}/submit")
    public int submitQuiz(@PathVariable Long id,
                          @RequestBody QuizSubmissionRequest request) {
        return quizService.submitQuiz(id, request);
    }
}
