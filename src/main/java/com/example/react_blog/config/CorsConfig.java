package com.example.react_blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings (CorsRegistry corsRegistry){
        corsRegistry
                .addMapping("/**") //모든 URL 경로에 대해 CORS 허용
                .allowedMethods("*") //모든 HTTP 메서드(GET, POST, PUT, DELETE 등) 허용
                .allowedOrigins("*"); //모든 출처(origin) 에 대해 접근 허용 (http://localhost:3000, https://example.com 등 아무거나 가능)
    }
}
