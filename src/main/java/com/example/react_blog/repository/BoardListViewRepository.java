package com.example.react_blog.repository;

import com.example.react_blog.entity.BoardListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {

    // 최신 리스트 조회
    List<BoardListViewEntity> findByOrderByWriteDatetimeDesc();
}
