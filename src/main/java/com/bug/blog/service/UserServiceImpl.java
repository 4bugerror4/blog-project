package com.bug.blog.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bug.blog.domain.RoleType;
import com.bug.blog.domain.User;
import com.bug.blog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	@Override
	public void save(User user) {
		
		String encPassword = encoder.encode(user.getPassword());
		
		user.setPassword(encPassword);
		user.setRole(RoleType.ROLE_USER);
		
		userRepository.save(user);
	}

	@Transactional
	@Override
	public void userUpdate(User reqUser) {
		
		User user = userRepository.findById(reqUser.getId()).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 아이디는 존재하지 않습니다. : " + reqUser.getId());
			}
		});
		
		String rawPassword = reqUser.getPassword();
		String encPassword = encoder.encode(rawPassword);
		
		user.setPassword(encPassword);
		user.setEmail(reqUser.getEmail());
		
	}

}
