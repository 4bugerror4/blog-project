package com.bug.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bug.blog.domain.RoleType;
import com.bug.blog.domain.User;
import com.bug.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입
	private UserRepository UserRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		
		// deleteById의 반환 값은 void 이기때문에 그냥 바로 쓰기는 위험함
		// try catch로 해당 아이디가 존재하지 않으면 예외처리 해주는것이 프로그램 종료를 막을 수 있다.
		try {
			UserRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제 실패하였습니다. 해당 아이디는 DB에 없습니다. : " + id;
		}		
		
		return "해당 아이디는 삭제되었습니다. : " + id;
	}
	
	// 유저정보를 수정할 때를 잘 생각해보자 password랑 email만 변경하면 된다
	// userid는 수정하면 안되는것이다
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {
		// JSON 으로 잘 받는지 POSTMAN으로 확인
		System.out.println("id : " + id);
		System.out.println("password : " + reqUser.getPassword());
		System.out.println("email : " + reqUser.getEmail());
		
		// 일단 먼저 findById로 변경할 User의 아이디 값으로 찾고
		// 그 찾은 아이디의 패스워드랑 이메일을 수정해야지 User의 빈값이 존재하지 않습니다.
		User user = UserRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 아이디는 찾을 수 없습니다. : " + id);
			}
		});
				
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		
		// 모든 유저를 다 던져 줌
		return UserRepository.findAll();
	}
	
	// 한페이지당 2건에 데이터를 리턴 받음
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size = 2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> reqUsers = UserRepository.findAll(pageable);
//		List<User> users = reqUsers.getContent();
		
		return reqUsers;
	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// findById의 반환타입은 Optinal이기 때문에 만약에 해당 아이디가 존재하지 않으면 에러메세지를 던줘야한다.
		User user = UserRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("해당 아이디는 존재하지 않습니다. : " + id);
			}
		});
		
		return user;
	}
	
	// http://localhost:8000/blog/dummy/join
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { // key=value (규칙)
		
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.ROLE_USER);
		UserRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}

}
