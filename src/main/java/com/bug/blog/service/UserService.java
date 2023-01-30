package com.bug.blog.service;

import com.bug.blog.domain.User;

public interface UserService {
	
	// 회원가입
	public void save(User user);
	
	// 회원수정
	public void userUpdate(User user);


}
