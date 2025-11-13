package com.example.dadambackend.domain.answer.controller;

import com.example.dadambackend.domain.answer.dto.request.CreateAnswerRequest;
import com.example.dadambackend.domain.answer.dto.response.AnswerResponse;
import com.example.dadambackend.domain.answer.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    // TEMP_USER_ID는 AnswerService에서 내부적으로 사용합니다.
    // private final Long TEMP_USER_ID = 1L; // 이 줄은 제거되었습니다.

    /**
     * 특정 질문에 답변을 작성합니다.
     * POST /api/v1/questions/{questionId}/answers
     */
    @PostMapping("/{questionId}/answers")
    public ResponseEntity<AnswerResponse> createAnswer(
            @PathVariable Long questionId,
            @Valid @RequestBody CreateAnswerRequest request) {

        // *******************************************************
        // 수정 필요 지점: TEMP_USER_ID 인자를 제거합니다.
        // *******************************************************
        AnswerResponse response = answerService.createAnswer(questionId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 특정 질문에 달린 답변 목록을 조회합니다.
     * GET /api/v1/questions/{questionId}/answers
     */
    @GetMapping("/{questionId}/answers")
    public ResponseEntity<List<AnswerResponse>> getAnswers(@PathVariable Long questionId) {
        List<AnswerResponse> responses = answerService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(responses);
    }
}