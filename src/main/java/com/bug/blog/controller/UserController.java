package com.bug.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 주소가 / 이면 index.jsp 허용
// static 이하에있는 /js/**, /css/**, /image/**

@Controller
public class UserController {

	@GetMapping("/auth/joinForm")
	public String joinForm() {

		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {

		return "user/loginForm";
	}
	
	@PostMapping("/auth/loginProc")
	public String loginProc() {

		return "user/loginForm";
	}
	
	@GetMapping("/user/userForm")
	public String updateForm() {
		
		return "user/updateForm";
	}

}
