package com.example.react_blog.entity.primaryKey;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FavoritePk implements Serializable { //자기자신 pk가 없는 entity강 있을 경우 이렇게 생성 하면 된다 == 복합키

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "board_number")
    private int boardNumber;
}