package com.example.react_blog.repository;

import com.example.react_blog.entity.FavoriteEntity;
import com.example.react_blog.entity.primaryKey.FavoritePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {

    FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber,String userEmail);
}
