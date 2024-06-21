---- 생성 순서 : 1번, 프로필 아이콘 더미
INSERT INTO profile_icon_tb (path, name, main, most_use, funny, popular, jooho_child_first, jooho_child_second)
VALUES
    ('/static/images/profiles/netflix-profile.png', 'Icon 1', true, false, true, true, false, false),
    ('/static/images/profiles/netflix-profile2.png', 'Icon 2', false, true, false, true, true, false),
    ('/static/images/profiles/netflix-profile3.png', 'Icon 3', false, false, true, false, false, true),
    ('/static/images/profiles/netflix-profile4.png', 'Icon 4', true, true, false, false, true, false),
    ('/static/images/profiles/netflix-profile5.png', 'Icon 5', false, false, true, true, false, true),
    ('/static/images/profiles/netflix-profile6.png', 'Icon 6', true, false, false, true, true, false),
    ('/static/images/profiles/netflix-profile7.png', 'Icon 7', false, true, true, false, false, true),
    ('/static/images/profiles/netflix-profile8.png', 'Icon 8', true, false, true, true, false, false),
    ('/static/images/profiles/netflix-profile9.png', 'Icon 9', false, true, false, false, true, true),
    ('/static/images/profiles/netflix-profile10.png', 'Icon 10', true, false, false, true, true, false);

-- 생성 순서 : 2번, 사용자 더미 1번사용자는 오플릭스 관리자.
INSERT INTO user_tb (email, password, username, mobile, profile_icon_id, status, user_save_rate, is_kids, login_save, is_auto_play, is_subscribe, created_at)
VALUES
    ('admin1@example.com', '1234', 'adminUser', '010-1111-1111', 1, 'ADMIN', 'R', false, true, true, true, NOW()),
    ('user2@example.com', '1234', 'user2', '010-2222-2222', 2, 'USER', 'ALL', false, false, true, true, NOW()),
    ('user3@example.com', '1234', 'user3', '010-3333-3333', 3, 'USER', 'ALL', true, false, true, false, NOW()),
    ('user4@example.com', '1234', 'user4', '010-4444-4444', 4, 'USER', 'PG', false, true, false, true, NOW()),
    ('user5@example.com', '1234', 'user5', '010-5555-5555', 5, 'USER', 'ALL', false, false, true, false, NOW()),
    ('user6@example.com', '1234', 'user6', '010-6666-6666', 6, 'USER', 'PG', true, true, false, true, NOW()),
    ('user7@example.com', '1234', 'user7', '010-7777-7777', 7, 'USER', 'ALL', false, false, false, true, NOW()),
    ('user8@example.com', '1234', 'user8', '010-8888-8888', 8, 'USER', 'PG', true, false, true, false, NOW()),
    ('user9@example.com', '1234', 'user9', '010-9999-9999', 9, 'USER', 'ALL', false, true, false, true, NOW()),
    ('user10@example.com', '1234', 'user10', '010-1111-0000', 10, 'USER','R', true, false, false, false, NOW());

-- 생성 순서 : 3번, 카드 정보 더미
INSERT INTO card_info_tb (user_id, card_number, last_digit, expiry_month, card_owner, date_of_birth, is_agreed_third)
VALUES
    (2, '1234567812345678', '5678', '12/23', 'User2', '1990-01-01', TRUE),
    (3, '8765432187654321', '4321', '11/22', 'User3', '1985-02-02', FALSE),
    (4, '1111222233334444', '4444', '10/21', 'User4', '1975-03-03', TRUE),
    (5, '5555666677778888', '8888', '09/20', 'User5', '2000-04-04', TRUE);


--- 생성 순서 : 4번, content 테이블
INSERT INTO content_tb (
    title, thumbnail, video_path, main_photo, poster_photo, text_photo,
    director, introduction, characteristic, play_time, product_year, writers,
    actors, view_count, rate, genre
) VALUES ('레디 플레이어 원', '/static/images/dummy/ready_player_one/thumbnail.webp', '/static/images/dummy/ready_player_one/video-path.mp4', '/static/images/dummy/ready_player_one/main-photo.webp', '/static/images/dummy/ready_player_one/poster-photo.jpg', '/static/images/dummy/ready_player_one/text-photo.webp', '스티븐 스필버그', '붕괴 직전의 암울한 세상. 재능 있는 게이머가 거대한 가상 현실 세계의 소유권을 얻기 위한 도전에 앞장선다. 모두의 꿈과 희망을 지키기 위해!', '상상의 나라, 흥미진진', '2시간 20분', '2018', '자크 펜, 어니스트 클라인', '타이 쉐리던, 올리비아 쿡, 벤 멘델슨, 리나 웨이츠, T.J. 밀러, 사이먼 페그, 마크 라이런스, 필립 자오, 모리사키 윈, 해나 존카먼', 3, 'ALL', 'SF'),
         ('메이즈 러너','/static/images/dummy/maze_runner/thumbnail.webp','/static/images/dummy/maze_runner/main-photo.webp','/static/images/dummy/maze_runner/main-photo.webp','/static/images/dummy/maze_runner/poster-photo.jpg','/static/images/dummy/maze_runner/text-photo.webp','웨스 볼','알 수 없는 곳에서, 기억을 잃은 채 무리 지어 살아가는 소년들. 그들이 어디서 온 누구지, 여기는 어디지 아무도 알지 못한다. 단 확실한 건, 이곳에서 탈출하려면 밤마다 괴성이 들리는 거대한 미로를 통과해야 한다는 것!','아드레날린 폭발, 긴장감 넘치는','1시간 54분','2014','노아 오페넘, 그랜트 피어스 마이어스, T.S. 나울린', '딜런 오브라이언, 카야 스코델라리오, 토마스 브로디생스터, 윌 폴터, 에멜 아민, 이기홍, 덱스터 다든, 퍼트리샤 클락슨', 5,'PG','SF'),
         ('블레이드러너2049','/static/images/dummy/blade_runner/thumbnail.webp','/static/images/dummy/blade_runner/main-photo.webp','/static/images/dummy/blade_runner/main-photo.webp','/static/images/dummy/blade_runner/poster-photo.webp','/static/images/dummy/blade_runner/text-photo.webp','드니 빌뇌브','수십 년 만에 모습을 드러낸 유골에는 어떤 비밀이 숨어 있었나. 진실을 찾으려는 경찰, 진실을 덮으려는 조직, 진실을 악용하려는 재계 거물의 쫓고 쫓기는 게임이 시작된다!','발상의 전환, 어두운','2시간 43분','2017','햄프턴 펜처, 마이클 그린','라이언 고슬링, 해리슨 포드, 아나 데 아르마스, 실비아 획스, 로빈 라이트, 매켄지 데이비스, 재러드 레토, 데이브 바티스타, 칼라 주리, 레니 제임스, 손 옥, 에드워드 제임스 올모스',6,'PG','SF');
