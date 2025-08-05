package com.example.react_blog.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    //이미지 업로드
    String upload(MultipartFile file);
    //이미지 조회
    Resource getImage(String fileName);
}