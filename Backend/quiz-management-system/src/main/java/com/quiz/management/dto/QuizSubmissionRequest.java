package com.quiz.management.dto;

import java.util.Map;

import lombok.Data;

@Data
public class QuizSubmissionRequest {

	private Map<Long, String> answers;
	
}
