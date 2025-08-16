package com.example.react_blog.entity;

import com.example.react_blog.dto.request.board.PatchBoardRequestDto;
import com.example.react_blog.dto.request.board.PostBoardRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_number")
    private int boardNumber;
    private String title;
    private String content;
    @Column(name = "write_datetime")
    private String writeDatetime;
    @Column(name = "favorite_count")
    private int favoriteCount;
    @Column(name = "comment_count")
    private int commentCount;
    @Column(name = "view_count")
    private int viewCount;
    @Column(name = "writer_email")
    private String writerEmail;

    public BoardEntity(PostBoardRequestDto dto,String email){

        //현재시간
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.title=dto.getTitle();
        this.content=dto.getContent();
        this.writeDatetime= writeDatetime;
        this.favoriteCount=0;
        this.commentCount=0;
        this.viewCount=0;
        this.writerEmail=email;
    }

    public void increaseViewCount() {
        this.viewCount ++;
    }

    public void increaseFavoriteCount() {
        this.favoriteCount ++;
    }

    public void decreaseFavoriteCount() {
        this.favoriteCount --;
    }

    public void increaseCommentCount() {
        this.commentCount ++;
    }

    public void patchBoard(PatchBoardRequestDto dto){
        this.title=dto.getTitle();
        this.content=dto.getContent();
    }
}
