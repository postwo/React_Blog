package com.example.react_blog.repository;

import com.example.react_blog.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
}
