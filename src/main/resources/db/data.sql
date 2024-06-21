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