package com.bug.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bug.blog.domain.User;

// 자동으로 bean 등록이 된다. @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);

}
