package com.example.react_blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board_list_view")
@Table(name = "board_list_view")
public class BoardListViewEntity { //뷰테이블은 entity 타입을 지정할 필요 없다

    @Id
    @Column(name = "board_number")
    private int boardNumber;
    private String title;
    private String content;
    @Column(name = "title_image")
    private String titleImage; //I
    @Column(name = "view_count")
    private int viewCount;
    @Column(name = "favorite_count")
    private int favoriteCount;
    @Column(name = "comment_count")
    private int commentCount;
    @Column(name = "write_datetime")
    private String writeDatetime;
    @Column(name = "writer_email")
    private String writerEmail; //U
    @Column(name = "writer_nickname")
    private String writerNickname; //U
    @Column(name = "writer_profile_image")
    private String writerProfileImage; //U
}