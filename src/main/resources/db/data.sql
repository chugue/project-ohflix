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



-- 사용자 생성 -- 1번사용자는 오플릭스 관리자.
INSERT INTO user_tb (email, password, username, profile_icon_id, status, is_kids, login_save, is_auto_play, is_subscribe, created_at)
VALUES
    ('admin1@example.com', '1234', 'adminUser', 1, 'ADMIN', false, true, true, true, NOW()),
    ('user2@example.com', '1234', 'user2', 2, 'USER', false, false, true, true, NOW()),
    ('user3@example.com', '1234', 'user3', 3, 'USER', true, false, true, false, NOW()),
    ('user4@example.com', '1234', 'user4', 4, 'USER', false, true, false, true, NOW()),
    ('user5@example.com', '1234', 'user5', 5, 'USER', false, false, true, false, NOW()),
    ('user6@example.com', '1234', 'user6', 6, 'USER', true, true, false, true, NOW()),
    ('user7@example.com', '1234', 'user7', 7, 'USER', false, false, false, true, NOW()),
    ('user8@example.com', '1234', 'user8', 8, 'USER', true, false, true, false, NOW()),
    ('user9@example.com', '1234', 'user9', 9, 'USER', false, true, false, true, NOW()),
    ('user10@example.com', '1234', 'user10', 10, 'USER', true, false, false, false, NOW());




-- 카드정보 더미 데이터
INSERT INTO card_info_tb (user_id, card_number, expiry_month, card_owner, date_of_birth, is_agreed_third)
VALUES
    (2, '9876543210987654', '09/23', 'Jane Smith', '1985-05-15', false),
    (3, '5555666677778888', '11/24', 'Michael Johnson', '1988-10-20', true),
    (4, '4444555566667777', '08/22', 'Emily Davis', '1995-03-30', false),
    (5, '1111222233334444', '07/21', 'Sophia Wilson', '1983-11-05', true),
    (6, '9999888877776666', '06/20', 'Daniel Brown', '1979-09-12', false),
    (7, '6666555577778888', '05/19', 'Olivia Martinez', '1992-04-25', true),
    (8, '3333444455556666', '04/18', 'Alexander Taylor', '1987-07-08', false),
    (9, '7777888899990000', '03/17', 'Isabella Anderson', '1998-02-15', true);