---- 생성 순서 : 1번, 프로필 아이콘 더미
INSERT INTO profile_icon_tb (path, name, main, most_use, funny, popular, jooho_child_first, jooho_child_second)
VALUES
    ('/static/images/profiles/netflix-profile.png', 'Icon 1', true, false, true, true, false, false),
    ('/static/images/profiles/netflix-profile2.png', 'Icon 2', false, true, false, true, false, false),
    ('/static/images/profiles/netflix-profile3.png', 'Icon 3', false, false, true, true, false, false),
    ('/static/images/profiles/netflix-profile4.png', 'Icon 4', true, true, false, true, false, false),
    ('/static/images/profiles/netflix-profile5.png', 'Icon 5', false, false, true, true, false, false),
    ('/static/images/profiles/netflix-profile6.png', 'Icon 6', true, false, false, true, false, false),
    ('/static/images/profiles/netflix-profile7.png', 'Icon 7', false, true, true, false, false, false),
    ('/static/images/profiles/netflix-profile8.png', 'Icon 8', true, false, true, true, false, false),
    ('/static/images/profiles/netflix-profile9.png', 'Icon 9', false, true, false, false, false, false),
    ('/static/images/profiles/netflix-profile10.png', 'Icon 10', true, false, false, true, false, false),
--     C3반 친구들 더미
    ('/static/images/profiles/netflix-profile31.png', 'Icon 31', false, false, false, false, true, false),
    ('/static/images/profiles/netflix-profile32.png', 'Icon 32', false, false, false, false, true, false),
    ('/static/images/profiles/netflix-profile33.png', 'Icon 33', false, false, false, false, true, false),
    ('/static/images/profiles/netflix-profile34.png', 'Icon 34', false, false, false, false, true, false),
    ('/static/images/profiles/netflix-profile35.png', 'Icon 35', false, false, false, false, true, false),
    ('/static/images/profiles/netflix-profile36.png', 'Icon 36', false, false, false, false, false, true),
    ('/static/images/profiles/netflix-profile37.png', 'Icon 37', false, false, false, false, false, true),
    ('/static/images/profiles/netflix-profile38.png', 'Icon 38', false, false, false, false, false, true),
    ('/static/images/profiles/netflix-profile39.png', 'Icon 39', false, false, false, false, false, true),
    ('/static/images/profiles/netflix-profile40.png', 'Icon 40', false, false, false, false, false, true);

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
    (2, '3334567812345678', '3333', '05/25', 'User2', '1990-01-01', TRUE),
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
         ('블레이드러너2049','/static/images/dummy/blade_runner/thumbnail.webp','/static/images/dummy/blade_runner/main-photo.webp','/static/images/dummy/blade_runner/main-photo.webp','/static/images/dummy/blade_runner/poster-photo.webp','/static/images/dummy/blade_runner/text-photo.webp','드니 빌뇌브','수십 년 만에 모습을 드러낸 유골에는 어떤 비밀이 숨어 있었나. 진실을 찾으려는 경찰, 진실을 덮으려는 조직, 진실을 악용하려는 재계 거물의 쫓고 쫓기는 게임이 시작된다!','발상의 전환, 어두운','2시간 43분','2017','햄프턴 펜처, 마이클 그린','라이언 고슬링, 해리슨 포드, 아나 데 아르마스, 실비아 획스, 로빈 라이트, 매켄지 데이비스, 재러드 레토, 데이브 바티스타, 칼라 주리, 레니 제임스, 손 옥, 에드워드 제임스 올모스',6,'PG','SF'),
         ('인터스텔라','/static/images/dummy/interstella/thumbnail.webp','/static/images/dummy/interstella/main-photo.webp','/static/images/dummy/interstella/main-photo.webp','/static/images/dummy/interstella/poster-photo.jpg','/static/images/dummy/interstella/text-photo.webp','크리스토퍼 놀란','인류 멸망이 목전에 닥친 미래. 우주비행사들이 웜홀을 통해 광활한 우주를 여행하며 인류가 살 수 있는 또 다른 행성을 찾아 나선다.','발상의 전환','2시간 49분','2014','조나선 놀란, 크리스토퍼 놀란','매튜 매커너히, 앤 해서웨이, 제시카 차스테인, 마이클 케인, 엘렌 버스틴, 존 리스고, 케이시 애플렉, 웨스 벤틀리, 빌 어윈, 매켄지 포이, 토퍼 그레이스, 데이빗 가야시',  2,'ALL','SF'),
         ('아틀라스','/static/images/dummy/atlas/thumbnail.jpg','/static/images/dummy/atlas/main-photo.webp','/static/images/dummy/atlas/main-photo.webp','/static/images/dummy/atlas/poster-photo.jpg','/static/images/dummy/atlas/text-photo.webp','브래드 페이튼','치명적인 AI 반란이 일어난 지 수십 년 후, 탁월한 데이터 분석가(제니퍼 로페즈)가 인류를 구할 유일한 희망이 바로 그 AI 기술에 달려 있다는 사실을 깨닫는다.','발상의 전환, 긴박감 넘치는','2시간','2024','레오 사다리안, 에런 얼라이 콜라이트','제니퍼 로페즈, 시무 류, 스털링 K. 브라운, 그레고리 제임스 코인, 마크 스트롱, 에이브러햄 포플라, 라나 파리야',  2 ,'PG','SF'),
         ('센과 치히로의 행방불명','/static/images/dummy/chihiro/thumbnail.webp','/static/images/dummy/chihiro/main-photo.jpg','/static/images/dummy/chihiro/main-photo.jpg','/static/images/dummy/chihiro/poster-photo.png','/static/images/dummy/chihiro/text-photo.webp','미야자키 하야오','마녀가 지배하는 신비한 세계에 발을 들여놓은 치히로, 마녀에게 거역하는 자는 동물로 변하게 되는데…','상상의 나래, 진심 어린','2시간 5분','2001','미야자키 하야오','히이라기 루미, 이리노 미유, 나쓰키 마리, 나이토 다카시, 사와구치 야스코, 가슈인 다쓰야, 카미키 류노스케, 다마이 유미, 오오이즈미 요, 스카와라 분타, 하야시코바',  2 ,'ALL','ANIME'),
         ('고양이의 보은','/static/images/dummy/thecatreturns/thumbnail.webp','/static/images/dummy/thecatreturns/main-photo.jpg','/static/images/dummy/thecatreturns/main-photo.jpg','/static/images/dummy/thecatreturns/poster-photo.png','/static/images/dummy/thecatreturns/text-photo.webp','모리타 히로유키','평범한 여고생 하루는 하굣길에 트럭에 치일 뻔한 고양이를 구해준다. 그런데 알고 보니 고양이 왕국의 왕자님이었다니! 보답으로 하루를 신부로 맞이하겠다고 하는데, 아무리 왕국이 멋지고 좋아도 평생 고양이로 살 순 없지 않을까.','유쾌 발랄, 상상의 나래, 기분 좋아지는','1시간 15분','2002','모리타 히로유키','이케와키 치즈루, 하카마다 요시히코, 마에다 아키, 야마다 타카유키, 사토 히토미, 사토이 겐타, 하마다 마리, 와타나베 데쓰, 사이토 요스케, 오카에 구미코, 단바 데쓰로',   2,'ALL','ANIME'),
         ('도라에몽: 스탠바이미','/static/images/dummy/doraemon/thumbnail.webp','/static/images/dummy/doraemon/thumbnail.webp','/static/images/dummy/doraemon/thumbnail.webp','/static/images/dummy/doraemon/poster-photo.png','/static/images/dummy/doraemon/text-photo.webp','야기 류이치, 야마자키 타카시','초등학교 4학년생 노진구는 둘도 없는 친구인 고양이 로봇 도라에몽이 미래로 돌아갈 수 있도록 착한 아이가 되고자 노력한다.','기분 좋아지는, 진심 어린','1시간 36분','2014','야기 류이치, 야마자키 타카시', '미즈타 와사비, 오하라 메구미, 카카즈 유미, 스바루 키무라, 세키 토모카즈, 츠마부키 사토시', 3,'ALL','ANIME');
INSERT INTO content_tb (
    title, thumbnail, video_path, main_photo, poster_photo, text_photo,
    director, introduction, characteristic, play_time, product_year, writers,
    actors, view_count, rate, genre
) VALUES('극장판 포켓몬스터: 너로 정했다!','/static/images/dummy/poketmonster/thumbnail.webp','/static/images/dummy/poketmonster/main-photo.webp','/static/images/dummy/poketmonster/main-photo.webp','/static/images/dummy/poketmonster/poster-photo.png','/static/images/dummy/poketmonster/text-photo.webp','유야마 구니히코','포켓몬을 받는 날인데 그만 늦잠을 잤네. 결국 마지막으로 남겨진 피카츄와 파트너가 된 지우. 피카츄의 고집 때문에 한바탕한 뒤 전설의 포켓몬 칠색조를 목격한다. 우리, 이 포켓몬을 찾으러 가볼래? 칠색조를 만나서 배틀을 해보고 싶어.','상상의 나래, 흥미진진','1시간36분','2017','유야마 구니히코','오타니 이쿠에, 사라 나토체니, 데이비드 올리버 넬슨, 마이크 폴록, 케이트 브리스틀, 수지 마이어스, 로저 파슨스',  5 ,'ALL','ANIME'),
        ('몬스터 주식회사','/static/images/dummy/monster_inc/thumbnail.webp','/static/images/dummy/monster_inc/main-photo.webp','/static/images/dummy/monster_inc/main-photo.webp','/static/images/dummy/monster_inc/poster-photo.png','/static/images/dummy/monster_inc/text-photo.webp','피트 닥터','아이들의 비명소리로 에너지를 만들어내는 몬스터 주식회사. 덩치 큰 설리(존 굳맨)와 수다쟁이 마이크(빌리 크리스탈)는 아이들 겁주기로 가장 인정받는 팀이다. 그러나 실수로 부라는 어린이가 몬스터 세계로 들어온 뒤, 부를 집으로 보내주기 위한 몬스터들의 좌충우돌 모험이 시작된다.','상상의 나래,  흥미진진','1시간 34분','2001','피트 닥터','존 굿맨, 빌리 크리스탈, 메리 깁스, 스티브 부세미, 제임스 코번, 제니퍼 틸리',  10 ,'ALL','ANIME'),
        ('나 홀로 집에','/static/images/dummy/home_alone/thumbnail.webp','/static/images/dummy/home_alone/main-photo.webp','/static/images/dummy/home_alone/main-photo.webp','/static/images/dummy/home_alone/poster-photo.png','/static/images/dummy/home_alone/text-photo.webp','크리스 콜럼버스','가족이 다 사라졌으면 좋겠어! 하나님이 소원을 들어주신 걸까? 크리스마스 연휴에 집에 혼자 남게 된 8살 소년. 혼자 놀기에 지칠 즈음, 집 주위를 얼쩡거리는 도둑들을 발견하는데. 들어올 테면 들어와 보시지. 우리 집은 내가 지킨다!','허당 매력','1시간 43분','1990','크리스 콜럼버스','맥컬리 컬킨, 조 페시, 다니엘 스턴, 존 허드, 로버츠 블로섬, 캐서린 오하라, 앤젤라 고설스, 데빈 러트레이, 게리 배먼, 힐러리 울프',   3 ,'ALL','COMEDY'),
        ('악마는 프라다를 입는다','/static/images/dummy/devil_wears_prada/thumbnail.webp','/static/images/dummy/devil_wears_prada/main-photo.webp','/static/images/dummy/devil_wears_prada/main-photo.webp','/static/images/dummy/devil_wears_prada/poster-photo.png','/static/images/dummy/devil_wears_prada/text-photo.webp','데이비드 프랭클','깐깐한 패션지 편집장의 비서로 일하게 된 사회 초년생. 성공을 위해 고군분투하는 동시에, 자신만의 강점과 스타일을 찾아나간다.','위트 있는, 기분 좋아지는','1시간 49분','2006','얼린 브로시 매케나','앤 해서웨이, 메릴 스트립, 에밀리 블런트, 스탠리 투치, 사이먼 베이커, 에이드리언 그레니에이, 트레이시 톰스, 재클린 티퍼니 브라운, 리치 서머, 다니엘 선자타', 10,'PG','COMEDY'),
        ('행오버','/static/images/dummy/hangover/thumbnail.webp','/static/images/dummy/hangover/main-photo.webp','/static/images/dummy/hangover/main-photo.webp','/static/images/dummy/hangover/poster-photo.png','/static/images/dummy/hangover/text-photo.webp','토드 필립스','라스베이거스에서 벌인 광란의 총각 파티 후 다음 날 아침. 절친 셋이 숙취에 시달리며 깨어나 보니 결혼식을 앞둔 친구가 사라졌다! 전날 일은 하나도 기억이 안 나는데 어쩌지?','아찔하고 발칙하게','1시간 39분','2009','스콧 무어, 존 루커스','브래들리 쿠퍼, 에드 헬름스, 잭 갤리퍼내키스, 저스틴 바사, 헤더 그레이엄, 사샤 바레즈, 제프리 탬보어, 켄 정, 레이철 해리스, 마이크 타이슨', 23,'R','COMEDY'),
        ('화이트칙스','/static/images/dummy/white_chicks/thumbnail.webp','/static/images/dummy/white_chicks/main-photo.webp','/static/images/dummy/white_chicks/main-photo.webp','/static/images/dummy/white_chicks/poster-photo.png','/static/images/dummy/white_chicks/text-photo.webp','키넌 아이보리 웨이언스','납치 음모를 저지해야 하는 두 흑인 FBI 요원. 자신들이 경호를 맡은 상속녀 시늉을 하려고 백인 여자로 변장한다.','아찔하고 발칙하게, 허당 매력','1시간 49분','2004','키넌 아이보리 웨이언스, 숀 웨이언스, 말런 웨이언스, 앤드루 매켈프레시, 마이클 앤서니 스노든, 제이비어 쿡','숀 웨이언스, 말런 웨이언스, 제이미 킹, 프랭키 페이슨, 로슬린 먼로, 존 허드, 비지 필립스, 테리 크루스, 브리트니 다니엘, 에디 베레즈, 메이틀랜드 워드',22,'PG','COMEDY');
/*        ('찰리와 초콜릿 공장',
        '/static/images/dummy/charlie_chocolate/thumbnail.webp',
        '/',
        '/',
        '/',
        '/',
        '',
        '',
        '',
        '',
        '',
        '',
        '',
        ,
        '',
        '');*/











-- 생성 순서 : 4번, 결재 내역 정보 더미
INSERT INTO purchase_history_tb (card_info_id, user_id, description, service_period, paymethod, amount, created_at)
VALUES
    -- user_id 2
    (1, 2, '스트리밍 서비스', '2024-06-14~2024-07-13', 'CREDITCARD', 13500, '2024-06-14 12:00:00'),
    (2, 2, '스트리밍 서비스', '2024-05-14~2024-06-13', 'CREDITCARD', 13500, '2024-05-14 10:30:00'),

    -- user_id 3
    (3, 3, '스트리밍 서비스', '2024-06-14~2024-07-13', 'KAKAOPAY', 13500, '2024-06-14 15:45:00'),
    (3, 3, '스트리밍 서비스', '2024-05-14~2024-06-13', 'KAKAOPAY', 13500, '2024-05-14 09:00:00'),

    -- user_id 4
    (4, 4, '스트리밍 서비스', '2024-06-14~2024-07-13', 'CREDITCARD', 13500, '2024-06-14 09:15:00'),
    (4, 4, '스트리밍 서비스', '2024-05-14~2024-06-13', 'CREDITCARD', 13500, '2024-05-14 14:20:00'),

    -- user_id 5
    (5, 5, '스트리밍 서비스', '2024-06-14~2024-07-13', 'KAKAOPAY', 13500, '2024-06-14 08:30:00'),
    (5, 5, '스트리밍 서비스', '2024-05-14~2024-06-13', 'KAKAOPAY', 13500, '2024-05-14 11:45:00');