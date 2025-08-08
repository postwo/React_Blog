package com.example.react_blog.repository;

import com.example.react_blog.entity.BoardEntity;
import com.example.react_blog.repository.resultSet.GetBoardResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {

    // 쿼리문 작성할때 꼭 " 이거 뒤에 한 칸 띄우기(중요)
    //DATE_FORMAT(b.write_datetime, '%Y-%m-%d %H:%i:%s') 이걸 이렇게 사용한 이유는 서버에서는 스트링 타입으로 받을려고 하기 때문
    @Query(value =
            "select " +
            "b.board_number as boardNumber, " +
            "b.title as title, " +
            "b.content as content, " +
            "DATE_FORMAT(b.write_datetime, '%Y-%m-%d %H:%i:%s') as writerDatetime, " +
            "b.writer_email as writerEmail, " +
            "u.nickname as writerNickname, " +
            "u.profile_image as writerProfileImage " +
            "from board as b " +
            "inner join user as u " +
            "on b.writer_email = u.email " +
            "where board_number = ?1 ", //boardNumber 에서 첫번째로 넘어오는 매개변수를 넘긴다는 의미이다
            nativeQuery = true
    )
    GetBoardResultSet getBoard(Integer boardNumber);

    BoardEntity findByboardNumber(Integer boardNumber);

    // 게시물 있는지 확인
    boolean existsByBoardNumber(Integer boardNumber);
}
