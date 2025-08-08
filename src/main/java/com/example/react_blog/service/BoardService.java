package com.example.react_blog.service;


import com.example.react_blog.dto.request.board.PostBoardRequestDto;
import com.example.react_blog.dto.response.board.GetBoardResponseDto;
import com.example.react_blog.dto.response.board.GetFavoriteListResponseDto;
import com.example.react_blog.dto.response.board.PostBoardResponseDto;
import com.example.react_blog.dto.response.board.PutFavoriteResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

    //게시물 불러오기
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

    //좋아요
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);

    //게시물 좋아요 리스트 가져오기
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);
}
