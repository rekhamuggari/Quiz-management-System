package com.quiz.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.management.dto.LoginRequest;
import com.quiz.management.dto.QuizRequest;
import com.quiz.management.service.QuizService;
import com.quiz.management.service.UsersService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private final UsersService userService;
    private final QuizService quizService;

    public AdminController(UsersService userService, QuizService quizService) {
        this.userService = userService;
        this.quizService = quizService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean success = userService.login(request.getUsername(), request.getPassword());
        return success ? ResponseEntity.ok("Login successful")
                       : ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/quiz")
    public ResponseEntity<?> createQuiz(@RequestBody QuizRequest request) {
        return ResponseEntity.ok(quizService.createQuiz(request));
    }
	
	
}
