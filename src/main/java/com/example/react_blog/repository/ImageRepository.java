package com.example.react_blog.repository;

import com.example.react_blog.entity.ImageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {

    List<ImageEntity> findByBoardNumber(Integer boardNumber);

    //Transactional=  게시글 삭제 ,관련 댓글 삭제 ,첨부파일 삭제 ,좋아요/조회수 기록 삭제 = "이 작업들 전부가 하나의 묶음으로 실행된다. 중간에 하나라도 실패하면 전체가 취소된다
    @Transactional
    void deleteByBoardNumber(Integer boardNumber);


}