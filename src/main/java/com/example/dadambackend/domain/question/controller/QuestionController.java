package com.example.dadambackend.domain.question.controller;

import com.example.dadambackend.domain.question.dto.response.QuestionResponse;
import com.example.dadambackend.domain.question.model.Question;
import com.example.dadambackend.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    /**
     * 오늘의 질문을 조회합니다. (가장 최근 질문)
     * GET /api/v1/questions/today
     */
    @GetMapping("/today")
    public ResponseEntity<QuestionResponse> getTodayQuestion() {
        Question question = questionService.getTodayQuestion();
        return ResponseEntity.ok(QuestionResponse.of(question));
    }

    /**
     * 특정 날짜의 질문을 조회합니다. (과거 질문 검색)
     * 롤백으로 인해 현재 QuestionService에 구현되지 않았습니다.
     * 컴파일 오류를 피하기 위해 주석 처리합니다.
     */
    /*
    @GetMapping // 파라미터가 있을 때 여기에 매핑됨
    public ResponseEntity<QuestionResponse> getQuestionByDate(
            @RequestParam(name = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Question question = questionService.getQuestionByDate(date);
        return ResponseEntity.ok(QuestionResponse.of(question));
    }
    */
}