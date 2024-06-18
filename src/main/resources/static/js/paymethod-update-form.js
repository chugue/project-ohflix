document.addEventListener("DOMContentLoaded", function () {
    const cardInput = document.getElementById("card-number");
    const expiryInput = document.getElementById("expiry-date");
    const nameInput = document.getElementById("name");
    const dobInput = document.getElementById("dob");
    const cardNumberHidden = document.getElementById('cardNumberHidden');

    function showErrorMessage(inputElement, message) {
        const formGroup = inputElement.closest(".form-group");
        let errorElement = formGroup.nextElementSibling; // 다음 형제 요소로 .error-message 찾기

        if (!errorElement || !errorElement.classList.contains("error-message")) {
            // .error-message 요소가 없는 경우 새로 생성
            errorElement = document.createElement("div");
            errorElement.classList.add("error-message");
            errorElement.style.color = "red";
            errorElement.style.fontSize = "0.8em";
            errorElement.style.marginTop = "30px";
            formGroup.parentNode.insertBefore(errorElement, formGroup.nextSibling);
        }

        errorElement.textContent = message;
        errorElement.style.display = "block";
        inputElement.style.borderColor = "red";
    }

    function removeErrorMessage(inputElement) {
        const formGroup = inputElement.closest(".form-group");
        const errorElement = formGroup.nextElementSibling; // 다음 형제 요소로 .error-message 찾기

        if (errorElement && errorElement.classList.contains("error-message")) {
            errorElement.textContent = "";
            errorElement.style.display = "none";
        }
        inputElement.style.borderColor = "#32CD32";
    }

    // 카드 번호 입력 처리 및 유효성 검사 (16자리 숫자)
    cardInput.addEventListener("input", function (e) {
        let value = e.target.value.replace(/\D/g, ''); // 숫자 외 문자 제거
        const cursorPosition = e.target.selectionStart;

        if (value.length > 16) {
            value = value.substring(0, 16);
        }

        let formattedValue = value.replace(/(.{4})/g, '$1 ').trim(); // 4자리마다 공백 추가

        // 입력된 숫자를 점으로 변환
        if (value.length === 16) {
            formattedValue = '•••• •••• •••• ' + value.substring(12);
            e.target.value = formattedValue;
            cardNumberHidden.value = value; // 원래 숫자 값을 숨겨진 input에 저장
            e.target.readOnly = true; // 입력을 막기 위해 readOnly 속성 추가
        }

        e.target.value = formattedValue;
        cardNumberHidden.value = value;

        const cardNumberPattern = /^\d{16}$/;
        if (cardNumberPattern.test(value)) {
            cardInput.classList.add("valid");
            cardInput.classList.remove("invalid");
            removeErrorMessage(cardInput);
        } else {
            cardInput.classList.add("invalid");
            cardInput.classList.remove("valid");
            showErrorMessage(cardInput, "유효한 카드 번호를 입력하세요 (16자리 숫자).");
        }

        let spaces = (formattedValue.slice(0, cursorPosition).match(/ /g) || []).length;
        e.target.setSelectionRange(cursorPosition + spaces, cursorPosition + spaces);
    });

    // 유효기간 유효성 검사 (MM/YY 형식)
    expiryInput.addEventListener("input", function (e) {
        let value = e.target.value.replace(/\D/g, ''); // 숫자 외 문자 제거

        if (value.length > 4) {
            value = value.substring(0, 4); // 최대 4자리까지 입력 허용
        }

        if (value.length >= 3) {
            value = value.slice(0, 2) + '/' + value.slice(2); // MM/YY 형식으로 변환
        }

        e.target.value = value;

        const expiryPattern = /^(0[1-9]|1[0-2])\/\d{2}$/;
        if (expiryPattern.test(value)) {
            expiryInput.classList.add("valid");
            expiryInput.classList.remove("invalid");
            removeErrorMessage(expiryInput);
        } else {
            expiryInput.classList.add("invalid");
            expiryInput.classList.remove("valid");
            showErrorMessage(expiryInput, "유효기간을 MM/YY 형식으로 입력하세요.");
        }
    });


    nameInput.addEventListener("input", function () {
        nameInput.value = nameInput.value.toUpperCase();
        const namePattern = /^[A-Za-z\s]+$/;
        if (namePattern.test(nameInput.value)) {
            nameInput.classList.add("valid");
            nameInput.classList.remove("invalid");
            removeErrorMessage(nameInput);
        } else {
            nameInput.classList.add("invalid");
            nameInput.classList.remove("valid");
            showErrorMessage(nameInput, "카드에 적힌 영어이름을 입력하세요.");
        }
    });

    dobInput.addEventListener("input", function () {
        const selectedDate = new Date(dobInput.value);
        const today = new Date();

        // 오늘의 날짜 이후인 경우 에러 메시지 표시
        if (selectedDate > today) {
            dobInput.classList.add("invalid");
            dobInput.classList.remove("valid");
            showErrorMessage(dobInput, "생년월일은 오늘 이전 날짜여야 합니다.");
        } else {
            dobInput.classList.add("valid");
            dobInput.classList.remove("invalid");
            removeErrorMessage(dobInput);
        }
    });
});
