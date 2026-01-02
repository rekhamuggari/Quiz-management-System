package com.quiz.management.dto;

import lombok.Data;

@Data
public class QuestionRequest {

	private String questionText;
	private String type;
	private String options;
	private String correctAnswer;

}
