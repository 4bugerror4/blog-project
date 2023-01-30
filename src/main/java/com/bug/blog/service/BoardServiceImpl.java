package com.bug.blog.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bug.blog.domain.Board;
import com.bug.blog.domain.Reply;
import com.bug.blog.domain.User;
import com.bug.blog.dto.ReplySaveRequestDto;
import com.bug.blog.repository.BoardRepository;
import com.bug.blog.repository.ReplyRepository;
import com.bug.blog.repository.UserRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;

	@Transactional
	@Override
	public void write(Board board, User user) {
		
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board); // title, content만 받음 
	}

	@Transactional(readOnly = true)
	@Override
	public List<Board> listAll() {
		return boardRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Board> listAllAndPage(Pageable pageable) {
		
		Page<Board> pBoards = boardRepository.findAll(pageable);
		
		return pBoards;
	}

	@Transactional(readOnly = true)
	@Override
	public Board detailBoard(int id) {
		
		return boardRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
			}
		});
	}

	@Transactional
	@Override
	public void deleteBoard(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void updateBoard(int id, Board reqBoard) {
		
		Board board = boardRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
			}
		});
		
		board.setTitle(reqBoard.getTitle());
		board.setContent(reqBoard.getContent());
		
	}

	@Transactional
	@Override
	public void replyWrite(User user, int boardId, Reply reqReply) {
		
		Board board = boardRepository.findById(boardId).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
			}
		});
		
		reqReply.setUser(user);
		reqReply.setBoard(board);
		
		replyRepository.save(reqReply);
		
	}

	// 댓글 쓰기 Dto 이용
	@Transactional
	@Override
	public void replyWrite(ReplySaveRequestDto reqReplyDto) {
		
		User user = userRepository.findById(reqReplyDto.getUserId()).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("댓글 쓰기 실패 : 유저 id를 찾을 수 없습니다.");
			}
		});
		
		Board board = boardRepository.findById(reqReplyDto.getBoardId()).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
			}
		});
		
		Reply reply = Reply.builder()
				.user(user)
				.board(board)
				.content(reqReplyDto.getContent())
				.build();
		
		replyRepository.save(reply);
		
	}

	@Transactional
	@Override
	public void replyWrite(int userId, int boardId, String content) {
		replyRepository.replySave(userId, boardId, content);
		
	}

	@Transactional
	@Override
	public void replyDelete(int replyId) {
		replyRepository.deleteById(replyId);
		
	}

}
