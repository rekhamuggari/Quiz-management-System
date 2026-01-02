package com.quiz.management.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.management.dto.QuestionRequest;
import com.quiz.management.dto.QuizRequest;
import com.quiz.management.dto.QuizSubmissionRequest;
import com.quiz.management.entity.Question;
import com.quiz.management.entity.Quiz;
import com.quiz.management.entity.Result;
import com.quiz.management.repository.QuizRepository;
import com.quiz.management.repository.ResultRepository;

@Service
public class QuizService {

	private final QuizRepository quizRepository;
	private final ResultRepository resultRepository;
	private final ObjectMapper objectMapper;

	public QuizService(QuizRepository quizRepository, ResultRepository resultRepository, ObjectMapper objectMapper) {
		this.quizRepository = quizRepository;
		this.resultRepository = resultRepository;
		this.objectMapper = objectMapper;
	}

	public Quiz createQuiz(QuizRequest request) {
		Quiz quiz = new Quiz();
		quiz.setTitle(request.getTitle());

		List<Question> questions = new ArrayList<>();
		for (QuestionRequest q : request.getQuestions()) {
			Question question = new Question();
			question.setQuestionText(q.getQuestionText());
			try {
				List<String> options =
				        objectMapper.readValue(q.getOptions(), new TypeReference<List<String>>() {});

				question.setOptions(objectMapper.writeValueAsString(options));

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			question.setCorrectAnswer(q.getCorrectAnswer());
			question.setQuiz(quiz);
			questions.add(question);
		}

		quiz.setQuestions(questions);
		return quizRepository.save(quiz);
	}

	public Quiz getQuiz(Long id) {
		Quiz quiz = quizRepository.findById(id).orElseThrow();
		Collections.shuffle(quiz.getQuestions());
		return quiz;
	}

	public int submitQuiz(Long quizId, QuizSubmissionRequest request) {
		Quiz quiz = quizRepository.findById(quizId).orElseThrow();
		int score = 0;

		for (Question q : quiz.getQuestions()) {
			String userAnswer = request.getAnswers().get(q.getId());
			if (q.getCorrectAnswer().equalsIgnoreCase(userAnswer.trim())) {
				score++;
			}
		}

		Result result = new Result();
		result.setQuiz(quiz);
		result.setScore(score);
		try {
			result.setUserAnswers(objectMapper.writeValueAsString(request.getAnswers()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		resultRepository.save(result);

		return score;

	}

	public List<Quiz> getAllQuiz() {
		return quizRepository.findAll();
	}
}
