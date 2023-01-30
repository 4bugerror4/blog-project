package com.bug.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bug.blog.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
