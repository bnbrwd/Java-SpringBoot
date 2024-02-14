package com.example.blogapi.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blogapi.entity.DBUser;
import com.example.blogapi.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		//get user from database
		DBUser dbUser = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("user not found with username or email: "+usernameOrEmail));
		
		//set authority
		Set<GrantedAuthority> authorities = dbUser.getRoles()
												.stream()
												.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		
		//convert this user object into a spring security provided user object
		
		return new User(dbUser.getEmail(),dbUser.getPassword(),authorities);
	}

}
