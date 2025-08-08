package com.example.react_blog.entity;

import com.example.react_blog.dto.request.board.PostCommentRequestDto;
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
@Entity(name = "comment")
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_number")
    private int commentNumber;
    private String content;
    @Column(name = "write_datetime")
    private String writeDatetime;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "board_number")
    private int boardNumber;

    public CommentEntity(PostCommentRequestDto dto, Integer boardNumber, String userEmail ) {
        this.content = dto.getContent();

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);
        this.writeDatetime = writeDatetime;

        this.userEmail = userEmail;
        this.boardNumber = boardNumber;
    }
}
