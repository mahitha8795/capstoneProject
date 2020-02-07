package com.capstone.service;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.model.User;
import com.capstone.repo.CapstoneRepository;

import reactor.core.publisher.Mono;

/**
 *
 * @author ard333
 */
@Service
public class UserService {
	
	@Autowired
	CapstoneRepository capstoneRepository;
	public Mono<User> findByUsername(String username) {
		/*if (username.equals(userUsername)) {
			return Mono.just(user);
		} else if (username.equals(adminUsername)) {
			return Mono.just(admin);
		} else {
			return Mono.empty();
		}*/
		if (null!= username && !username.isEmpty()) {
			return capstoneRepository.findById(username);
		} else {
			return Mono.empty();
		}
	}
	
}
