package com.example.react_blog.service;


import com.example.react_blog.dto.request.board.PostBoardRequestDto;
import com.example.react_blog.dto.request.board.PostCommentRequestDto;
import com.example.react_blog.dto.response.board.*;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

    //게시물 불러오기
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

    //좋아요
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);

    //게시물 좋아요 리스트 가져오기
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);

    //댓글 작성
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto,Integer boardNumber,String email);

    //댓글 불러오기
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);
}
