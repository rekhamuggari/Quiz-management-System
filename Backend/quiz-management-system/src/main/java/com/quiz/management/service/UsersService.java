package com.quiz.management.service;

import org.springframework.stereotype.Service;

import com.quiz.management.repository.UsersRepository;

@Service
public class UsersService {

	 private final UsersRepository userRepository;

	    public UsersService(UsersRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    public boolean login(String username, String password) {
	        return userRepository.findByUsername(username)
	                .map(a -> a.getPassword().equals(password))
	                .orElse(false);
	    }
	
}
