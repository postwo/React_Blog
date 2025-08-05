package com.example.react_blog.controller;

import com.example.react_blog.dto.response.user.GetSignInUserResponseDto;
import com.example.react_blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 유저정보 불러오기
    // 이거를 테스트할떄는 로그인을 먼저한후 테스트해야한다
    //JwtAuthenticationFilter에서 UsernamePasswordAuthenticationToken 에 내가담은 email을 꺼내올때 AuthenticationPrincipal을 사용
    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
            @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(email);
        return response;
    }
}
