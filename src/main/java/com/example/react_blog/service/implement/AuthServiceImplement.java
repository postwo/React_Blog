package com.example.react_blog.service.implement;

import com.example.react_blog.dto.request.SignUpRequestDto;
import com.example.react_blog.dto.response.ResponseDto;
import com.example.react_blog.dto.response.auth.SignUpResponseDto;
import com.example.react_blog.entity.UserEntity;
import com.example.react_blog.repository.UserRepository;
import com.example.react_blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//Impl은 인터페이스의 구현체를 의미한다
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //회원가입
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {


        try{
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if (existedEmail) return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname) return SignUpResponseDto.duplicateNickname();

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if (existedTelNumber) return SignUpResponseDto.duplicateTelNumber();

            dto.setPassword(passwordEncoder.encode(dto.getPassword()));

            UserEntity userEntity = new UserEntity(dto);

            userRepository.save(userEntity);


        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }




}
