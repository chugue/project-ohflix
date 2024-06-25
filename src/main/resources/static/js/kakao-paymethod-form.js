// 카카오페이 초기화
Kakao.init('2f8ef258958bfa2946ff5d726faefac7'); // 'YOUR_KAKAO_APP_KEY'를 실제 카카오 앱 키로 교체하세요.

function requestKakaoPay() {
    Kakao.Auth.authorize({
        redirectUri: 'http://localhost:8080/api/kakaoPaySuccess' // 리다이렉트 URI 설정
    });
}