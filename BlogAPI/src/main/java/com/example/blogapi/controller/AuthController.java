package com.example.blogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogapi.payload.JWTAuthResponse;
import com.example.blogapi.payload.LoginDto;
import com.example.blogapi.payload.RegisterDto;
import com.example.blogapi.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/auth")
//@Tag(name = "Auth controller exposes signin and signup REST API")
public class AuthController {

	@Autowired
	private AuthService authService;

	// Build Login Rest api
	@PostMapping("/login")
	@Operation(summary = "User Login", description = "Returns a token after logged")
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
		String token = authService.login(loginDto);
		JWTAuthResponse response = new JWTAuthResponse();
		response.setAccessToken(token);
		return ResponseEntity.ok(response);
	}

	// Build Login Rest api
	@PostMapping("/register")
	//@PostMapping(value = {"/register", "/signup"}) multiple endpoints
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		String response = authService.register(registerDto);
		return ResponseEntity.ok(response);
	}
}
