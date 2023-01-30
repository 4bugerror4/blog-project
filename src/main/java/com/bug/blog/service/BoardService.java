package com.bug.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bug.blog.domain.Board;
import com.bug.blog.domain.Reply;
import com.bug.blog.domain.User;
import com.bug.blog.dto.ReplySaveRequestDto;

public interface BoardService {
	
	// 글 쓰기
	public void write(Board board, User user);
	
	// 글 전체 목록 가져오기
	public List<Board> listAll();
	
	// 글 전체 목록 가져오기 and 페이지 처리
	public Page<Board> listAllAndPage(Pageable pageable);
	
	// 글 상세 보기
	public Board detailBoard(int id);
	
	// 글 삭제
	public void deleteBoard(int id);
	
	// 글 수정
	public void updateBoard(int id, Board board);
	
	// 댓글 쓰기
	public void replyWrite(User user, int boardId, Reply reqReply);
	
	// 댓글 Dto 이용해서 쓰기 (오버로딩)
	public void replyWrite(ReplySaveRequestDto reqReplyDto);
	
	// 댓글 NativeQuery 이용해서 쓰기 (오버로딩)
	public void replyWrite(int userId, int boardId, String content);
	
	// 댓글 삭제
	public void replyDelete(int replyId);

}
