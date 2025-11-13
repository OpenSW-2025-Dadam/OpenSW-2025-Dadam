package com.example.dadambackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**") // 모든 /api/v1 경로에 대해 CORS 허용
                .allowedOrigins(
                        "http://localhost:3000", // 프론트엔드 개발 환경
                        "http://localhost:8080" // Swagger/같은 도메인 환경 (와일드카드 "*" 제거)
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true) // 인증 정보 허용 시 "*" 사용 불가
                .maxAge(3600);
    }
}