package com.bug.blog.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // User 클래스가 MySQL에 테이블이 생성된다.
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전력을 따라간다.
	private int id; // auto_increment
	
	@Column(nullable = false, length = 30, unique = true) // 아이디는 유니크해야한다.(중복이 있으면 안됨)
	private String username; // id
	
	@Column(nullable = false, length = 100)
	private String password; // password
	
	@Column(nullable = false, length = 50)
	private String email; // email
	
	@Enumerated(EnumType.STRING) // DB에 Enum타입의 String이라고 알려줌
	private RoleType role; // Enum을 사용해서 작업
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;

}
