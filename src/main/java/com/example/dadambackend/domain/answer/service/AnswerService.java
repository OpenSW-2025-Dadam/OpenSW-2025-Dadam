package com.example.dadambackend.domain.answer.service;

import com.example.dadambackend.domain.answer.dto.request.CreateAnswerRequest;
import com.example.dadambackend.domain.answer.dto.response.AnswerResponse;
import com.example.dadambackend.domain.answer.model.Answer;
import com.example.dadambackend.domain.answer.repository.AnswerRepository;
import com.example.dadambackend.domain.question.model.Question;
import com.example.dadambackend.domain.question.service.QuestionService;
import com.example.dadambackend.domain.user.model.User;
import com.example.dadambackend.domain.user.repository.UserRepository;
import com.example.dadambackend.global.exception.BusinessException;
import com.example.dadambackend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final UserRepository userRepository;

    // 요청하신 대로 임시 사용자 ID를 1L로 고정합니다.
    private final Long TEMP_USER_ID = 1L;

    /**
     * 특정 질문(questionId)에 대한 답변을 작성합니다.
     * @param questionId 질문 ID
     * @param request 답변 요청 DTO
     * @return 생성된 답변 DTO
     */
    @Transactional
    public AnswerResponse createAnswer(Long questionId, CreateAnswerRequest request) {
        // 1. Question 존재 및 유효성 검사 (getQuestionById 사용)
        Question question = questionService.getQuestionById(questionId);

        // 2. 답변 작성자 조회 (임시 TEMP_USER_ID 사용)
        User user = userRepository.findById(TEMP_USER_ID)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 3. 중복 답변 검사 (현재는 임시 사용자 ID 1L 기준)
        if (answerRepository.existsByQuestionIdAndUserId(questionId, TEMP_USER_ID)) {
            throw new BusinessException(ErrorCode.ALREADY_ANSWERED);
        }

        // 4. 답변 저장
        Answer answer = new Answer(question, user, request.getContent());
        answer = answerRepository.save(answer);

        return AnswerResponse.of(answer);
    }

    /**
     * 특정 질문에 대한 모든 답변을 조회합니다.
     * @param questionId 질문 ID
     * @return 답변 목록 DTO
     */
    public List<AnswerResponse> getAnswersByQuestionId(Long questionId) {
        // 질문 유효성 검사 (Question ID가 유효하지 않으면 404 발생)
        questionService.getQuestionById(questionId);

        List<Answer> answers = answerRepository.findByQuestionIdOrderByCreatedAtAsc(questionId);
        return answers.stream()
                .map(AnswerResponse::of)
                .collect(Collectors.toList());
    }
}