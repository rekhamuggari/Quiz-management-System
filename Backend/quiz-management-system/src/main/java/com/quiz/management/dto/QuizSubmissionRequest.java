package com.quiz.management.dto;

import java.util.Map;

import lombok.Data;

@Data
public class QuizSubmissionRequest {

	private Map<Long, String> answers;

	public Map<Long, String> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<Long, String> answers) {
		this.answers = answers;
	}
	
	
}
