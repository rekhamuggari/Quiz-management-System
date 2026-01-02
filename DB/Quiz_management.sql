show databases;

CREATE DATABASE quiz_management_system;
USE quiz_management_system;

drop table admin;

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

INSERT INTO users (username, password)
VALUES ('admin', 'admin123');

CREATE TABLE quiz (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE question (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quiz_id BIGINT NOT NULL,
    question_text TEXT NOT NULL,
    options JSON NULL,
    correct_answer VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_question_quiz
        FOREIGN KEY (quiz_id)
        REFERENCES quiz(id)
        ON DELETE CASCADE
);

CREATE TABLE result (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quiz_id BIGINT NOT NULL,
    score INT NOT NULL,
    user_answers JSON NOT NULL,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_result_quiz
        FOREIGN KEY (quiz_id)
        REFERENCES quiz(id)
        ON DELETE CASCADE
);

INSERT INTO quiz (title)
VALUES ('Sample Quiz');

INSERT INTO question (quiz_id, question_text, type, options, correct_answer)
VALUES
(1, 'What is 2 + 2?', 'MCQ', '["1","2","3","4"]', '4'),
(1, 'The sun rises in the east.', 'TRUE_FALSE', NULL, 'true'),
(1, 'What is the capital of India?', 'TEXT', NULL, 'New Delhi');


