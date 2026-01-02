package com.quiz.management.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuizRequest {

	private String title;
	private List<QuestionRequest> questions;

}
