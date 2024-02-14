package com.example.blogapi.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blogapi.entity.DBUser;
import com.example.blogapi.entity.Role;
import com.example.blogapi.exception.BlogAPIException;
import com.example.blogapi.payload.LoginDto;
import com.example.blogapi.payload.RegisterDto;
import com.example.blogapi.repository.RoleRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.security.JwtTokenProvider;
import com.example.blogapi.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	// login api
	@Override
	public String login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		return token;
	}

	// register api
	@Override
	public String register(RegisterDto registerDto) {
		// add check for username is exist in database
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "username is already exist!");
		}

		// add check for email is exist in database
		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "email is already exist!");
		}
		
		DBUser user = new DBUser();
		user.setEmail(registerDto.getEmail());
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		
		userRepository.save(user);
		
		return "User registered successfully";
	}

}
