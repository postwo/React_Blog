package com.example.react_blog.repository;

import com.example.react_blog.entity.CommentEntity;
import com.example.react_blog.repository.resultSet.GetCommentListResultSet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    // as 에 작성한 거랑 GetCommentListResultSet 에서 작서한 필드들하고
    // get빼고 똑같이 해야 데이터를 잘 받아온다(중요)
    @Query(value =
            "select " +
            "c.content as content, " +
            "DATE_FORMAT(c.write_datetime, '%Y-%m-%d %H:%i:%s') as writeDatetime, " +
            "u.nickname as nickname, " +
            "u.profile_image as profileImage " +
            "from comment as c " +
            "inner join user as u " +
            "on c.user_email = u.email " +
            "where c.board_number = ?1 " +
            "order by c.comment_number desc",
            nativeQuery = true
    )
    List<GetCommentListResultSet> getCommentList(Integer boardNumber);

    //게시물 삭제
    //Transactional=  게시글 삭제 ,관련 댓글 삭제 ,첨부파일 삭제 ,좋아요/조회수 기록 삭제 = "이 작업들 전부가 하나의 묶음으로 실행된다. 중간에 하나라도 실패하면 전체가 취소된다
    @Transactional
    void deleteByBoardNumber(Integer boardNumber);

}
