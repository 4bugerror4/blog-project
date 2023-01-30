package com.bug.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	// yml 파일 설정으로 프로젝트 시작점 주소를 /blog로 설정했고 port 번호도 8000으로 변경했다
	// http://localhost:8000/blog/temp/jsp
	@GetMapping("/temp/jsp")
	public String tempHome() {
		
		// yml 파잃 설정으
		// 앞에는 prefix 가 붙을 것이고
		// 뒤에는 suffix 가 붙을 것이다
		return "test";
	}

}
