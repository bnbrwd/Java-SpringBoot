package com.example.blogapi.service;

import com.example.blogapi.payload.LoginDto;
import com.example.blogapi.payload.RegisterDto;

public interface AuthService {
	
	String login(LoginDto loginDto);
	
	String register(RegisterDto registerDto);
}
