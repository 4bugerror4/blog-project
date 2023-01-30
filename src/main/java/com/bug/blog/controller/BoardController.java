package com.bug.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bug.blog.domain.Board;
import com.bug.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size=4, sort="id", direction = Direction.DESC) Pageable pageable) {
		
		Page<Board> boards = boardService.listAllAndPage(pageable);
		
		int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4); 
		
		model.addAttribute("boards", boards); // pageabel boards
        model.addAttribute("number", boards.getNumber()); // 현재 페이지
        model.addAttribute("startPage", startPage); // 시작 페이지
        model.addAttribute("endPage", endPage); // 끝 페이지
        model.addAttribute("hasNext", boards.hasNext()); // 다음 페이지 있으면 true, 없으면 false
       
		return "index";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		
		model.addAttribute("board", boardService.detailBoard(id));
		
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		
		model.addAttribute("board", boardService.detailBoard(id));
		
		return "board/updateForm";
	}
	
	@GetMapping("/board/form")
	public String saveForm() {
		
		return "board/saveForm";
	}

}
