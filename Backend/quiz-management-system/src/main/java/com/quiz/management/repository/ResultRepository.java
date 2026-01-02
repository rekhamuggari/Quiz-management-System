package com.quiz.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.management.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

}
