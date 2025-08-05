package com.example.react_blog.service;


import com.example.react_blog.dto.request.board.PostBoardRequestDto;
import com.example.react_blog.dto.response.board.PostBoardResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
}
