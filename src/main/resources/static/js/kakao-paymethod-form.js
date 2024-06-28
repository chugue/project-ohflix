// 카카오페이 초기화
Kakao.init('c6c4256224a8bbce98f62df3b9dda93f'); // 'YOUR_KAKAO_APP_KEY'를 실제 카카오 앱 키로 교체하세요.

// 결제 버튼 클릭 시 결제 준비 요청을 보내고, 리다이렉트 URL로 이동
document.addEventListener('DOMContentLoaded', function() {
    const paymentButton = document.getElementById('paymentButton');
    if (paymentButton) {
        paymentButton.addEventListener('click', function() {
            fetch('http://localhost:8080/kakaoPay/ready', { // 절대 경로 사용
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userId: 2, // 실제 유저 ID로 변경
                    itemName: 'Test Item', // 실제 아이템 이름으로 변경
                    totalAmount: 1000, // 실제 금액으로 변경
                    vatAmount: 100 // 실제 VAT 금액으로 변경
                })
            })
                .then(response => response.json())
                .then(data => {
                    if (data.redirectUrl) {
                        window.location.href = data.redirectUrl;
                    }
                });
        });
    } else {
        console.error('Payment button not found');
    }
});
