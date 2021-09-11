package com.dong.jwtService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dong.jwtService.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

}
