package com.quiz.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.management.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);
	
}
