package com.capstone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.model.AuthRequest;
import com.capstone.model.AuthResponse;
import com.capstone.service.UserService;
import com.capstone.util.JWTUtil;
import com.capstone.util.PBKDF2Encoder;

import reactor.core.publisher.Mono;

/**
 *
 * @author ard333
 */
@RestController
public class AuthenticationREST {


	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Autowired
	private UserService userRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
		Logger logger = LoggerFactory.getLogger(AuthenticationREST.class);
		return userRepository.findByUsername(ar.getUsername()).map((userDetails) -> {
			if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
				logger.info("Found matching username and pwd");
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}


}
