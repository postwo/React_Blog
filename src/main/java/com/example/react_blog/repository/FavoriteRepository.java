package com.example.react_blog.repository;

import com.example.react_blog.entity.FavoriteEntity;
import com.example.react_blog.entity.primaryKey.FavoritePk;
import com.example.react_blog.repository.resultSet.GetFavoriteListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {

    FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber,String userEmail);

    // 게시물 좋아요 리스트 가져오기
    // as 에 작성한 거랑 GetFavoriteListResultSet 에서 작서한 필드들하고
    // get빼고 똑같이 해야 데이터를 잘 받아온다(중요)
    @Query(
            value=
            "select " +
            "u.email as email, " +
            "u.nickname as nickname, " +
            "u.profile_image as profileImage " +
            "from favorite as f " +
            "inner join user as u " +
            "on f.user_email = u.email " +
            "where f.board_number = ?1",
            nativeQuery = true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Integer boardNumber);
}
