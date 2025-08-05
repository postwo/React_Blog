package com.example.react_blog.service.implement;

import com.example.react_blog.dto.response.ResponseDto;
import com.example.react_blog.dto.response.user.GetSignInUserResponseDto;
import com.example.react_blog.entity.UserEntity;
import com.example.react_blog.repository.UserRepository;
import com.example.react_blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

        UserEntity userEntity = null;

        try{
            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetSignInUserResponseDto.notExistUser();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }
}