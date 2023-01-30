package com.bug.blog.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // summernote library 사용
	
	private int count;
	
	@ManyToOne // 연관관계 : 하나의 유저는 여러개의 게시글을 가질 수 있다
	@JoinColumn(name = "userId") // 해당 컬럼의 이름은 userId 이다
	private User user; // DB는 Object를 저장할 수 없음 만약 MyBatis를 이용해서 작업한다면 POREGINE KEY
																						   // casecade의 경우 board를 지울 떄 reply도 한번에 같이 지움
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // 연관관계 : 여러개의 리플은 하나의 게시글에 달릴 수 있다. 
	@JsonIgnoreProperties({"board"}) // 무한참조 방지하기 위한 어노테이션 jackson이 json으로 변경해 줄때 replys의 board는 더 이상 호출 안함
	@OrderBy("id desc") // desc 내림차순 asc 오름차순
	private List<Reply> replys; // mappedBy : board에는 replys라는 column을 가질 필요가없다 연관관계의 주인이 아니라는 뜻
	
	@CreationTimestamp
	private Timestamp createDate;

}
