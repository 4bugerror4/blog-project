package com.bug.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bug.blog.auth.PrincipalDetails;
import com.bug.blog.domain.Board;
import com.bug.blog.domain.Reply;
import com.bug.blog.dto.ReplySaveRequestDto;
import com.bug.blog.dto.ResponseDto;
import com.bug.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principal) {
		boardService.write(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.deleteBoard(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		boardService.updateBoard(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	
	}
	
//	@PostMapping("/api/board/{boardId}/reply")
//	public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetails principal) {
//		
//		boardService.replyWrite(principal.getUser(), boardId, reply);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	
//	}
	
	// Data Transfer Object 사용
//	@PostMapping("/api/board/{boardId}/reply")
//	public ResponseDto<Integer> replySaveDto(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
//		
//		boardService.replyWrite(replySaveRequestDto);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	
//	}
	
	// nativeQuery 사용
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySaveDto(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		
		boardService.replyWrite(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
		System.out.print("??????");
		boardService.replyDelete(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	

}
