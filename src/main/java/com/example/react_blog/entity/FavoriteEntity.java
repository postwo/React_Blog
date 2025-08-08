package com.example.react_blog.entity;

import com.example.react_blog.entity.primaryKey.FavoritePk;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "favorite")
@Table(name = "favorite")
@IdClass(FavoritePk.class) // == 복합키 표시, 이테이블 id들은 favoritePk를 따른다
public class FavoriteEntity {
    @Id
    private String userEmail;
    @Id
    private int boardNumber;
}
