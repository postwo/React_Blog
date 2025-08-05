package com.example.react_blog.service.implement;

import com.example.react_blog.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImplement implements FileService {//impl 은 인터페이스 구현체를 의미한다

    @Value("${spring.file.path}")
    private String filePath;
    @Value("${spring.file.url}")//파일이 들어올때 어떠한 url로 접근하는지
    private String fileUrl;

    //업로드
    @Override
    public String upload(MultipartFile file) {
        if(file.isEmpty()) return null; // 빈파일인지 검사

        String originFileName = file.getOriginalFilename();
        String extension = originFileName.substring(originFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;

        try{
            file.transferTo(new File(savePath)); // 해당경로에 파일을 저장
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        String url = fileUrl + saveFileName;
        return url;
    }

    //이미지를 받아온다
    @Override
    public Resource getImage(String fileName) {

        Resource resource = null;

        try{
            //UrlResource는 "이 파일 있어요~ 자, 여기서 꺼내 쓸게요" 하는 역할
            resource = new UrlResource("file:" + filePath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return resource;
    }



}
