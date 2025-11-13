-- V2__insert_swagger_test_data.sql

-- 1. 테스트 사용자 삽입 (3명)
-- User ID 1: 답변 작성 POST 테스트용으로 사용됩니다.
INSERT INTO app_user (id, email) VALUES
(1, 'parent_user_1@dadam.com'),
(2, 'child_user_2@dadam.com'),
(3, 'child_user_3@dadam.com');

-- 2. 테스트 질문 삽입 (3개)
-- ID 1, 2, 3으로 테스트 가능
INSERT INTO question (id, content, category, created_at) VALUES
(1, '가족과 함께한 가장 즐거웠던 여행은 무엇인가요?', 'TRAVEL', NOW()),
(2, '요즘 가족 구성원 각자가 빠져 있는 취미는?', 'HOBBY', NOW()),
(3, '서로에게 가장 고마웠던 순간 하나씩 이야기해 볼까요?', 'MEMORY', NOW());


-- 3. 답변 데이터 삽입 (총 9개, 각 질문당 3개 답변)

-- [테스트 목표 1: Question ID 1에 대한 답변 목록 조회]
INSERT INTO answer (question_id, user_id, content, created_at) VALUES
(1, 2, '작년에 갔던 해외여행! 공항에서 길 잃어버릴 뻔한 게 짜릿했어요.', NOW()),
(1, 3, '그냥 집 근처 공원에서 텐트 치고 놀았던 주말이 제일 편하고 즐거웠어요.', NOW() + INTERVAL '1' MINUTE);
-- User ID 1은 POST 테스트를 위해 의도적으로 답변을 남기지 않습니다.

-- [테스트 목표 2: Question ID 2에 대한 답변 목록 조회]
INSERT INTO answer (question_id, user_id, content, created_at) VALUES
(2, 1, '요즘 저는 주말에 새로운 레시피로 요리하는 것에 푹 빠져 있어요.', NOW()),
(2, 2, '저는 게임이요! 특히 다같이 할 수 있는 보드 게임을 다시 모으고 있어요.', NOW() + INTERVAL '1' MINUTE),
(2, 3, '저는 그림 그리기요. 가족들 몰래 방에서 열심히 그리고 있어요.', NOW() + INTERVAL '2' MINUTE);

-- [테스트 목표 3: Question ID 3에 대한 답변 목록 조회]
INSERT INTO answer (question_id, user_id, content, created_at) VALUES
(3, 1, '힘들 때 말없이 어깨를 토닥여준 순간이 가장 고마웠어요.', NOW()),
(3, 2, '생일날 깜짝 이벤트 해줬을 때요! 평생 잊지 못할 거예요.', NOW() + INTERVAL '1' MINUTE),
(3, 3, '제가 잘못했을 때 혼내지 않고 차분히 이야기해 줬던 그날이 기억에 남아요.', NOW() + INTERVAL '2' MINUTE);