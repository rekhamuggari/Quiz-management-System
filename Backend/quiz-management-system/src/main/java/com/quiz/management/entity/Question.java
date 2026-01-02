package com.quiz.management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "question_text", nullable = false)
	private String questionText;

	@Column(name = "options")
	private String options;

	@Column(name = "correct_answer", nullable = false)
	private String correctAnswer;

	@ManyToOne
	@JoinColumn(name = "quiz_id")
	@JsonBackReference
	private Quiz quiz;
}
