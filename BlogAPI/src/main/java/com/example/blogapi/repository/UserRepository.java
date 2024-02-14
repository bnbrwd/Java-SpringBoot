package com.example.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogapi.entity.DBUser;


public interface UserRepository extends JpaRepository<DBUser, Long>{
	
	Optional<DBUser> findByEmail(String email);
	
	Optional<DBUser> findByUsernameOrEmail(String username, String email);
	
	Optional<DBUser> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);

}
