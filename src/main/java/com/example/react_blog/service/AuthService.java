package com.example.react_blog.service;

import com.example.react_blog.dto.request.auth.SignInRequestDto;
import com.example.react_blog.dto.request.auth.SignUpRequestDto;
import com.example.react_blog.dto.response.auth.SignInResponseDto;
import com.example.react_blog.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    // ? 는 부모타입도 같이 반환하기 위해 사용
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto signUpRequestDto);

    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto signInRequestDto);
}
