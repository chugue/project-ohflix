// 카카오페이 초기화
Kakao.init('2f8ef258958bfa2946ff5d726faefac7'); // 'YOUR_KAKAO_APP_KEY'를 실제 카카오 앱 키로 교체하세요.

// 결제 버튼 클릭 시 결제 준비 요청을 보내고, 리다이렉트 URL로 이동
document.getElementById('paymentButton').addEventListener('click', function() {
    fetch('/api/payment/ready', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            userId: 2, // 실제 유저 ID로 변경
            itemName: '테스트 상품',
            totalAmount: 1000,
            vatAmount: 100,
        }),
    })
        .then(response => response.json())
        .then(data => {
            window.location.href = data.redirectUrl;
        });
});
