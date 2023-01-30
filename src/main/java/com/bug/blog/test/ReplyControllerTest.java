package com.bug.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bug.blog.domain.Board;
import com.bug.blog.service.BoardService;

@RestController
public class ReplyControllerTest {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/auth/board/{id}")
	public Board loopTest(@PathVariable int id) {
		return boardService.detailBoard(id);
	}

}
