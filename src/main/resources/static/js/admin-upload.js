// 영화 업로드
document.getElementById('file-upload').addEventListener('change', function () {
    var fileName = this.files[0] ? this.files[0].name : '선택된 파일 없음';
    document.getElementById('file-name1').textContent = fileName;
});

// 썸네일
document.getElementById('image-upload1').addEventListener('change', function () {
    var fileName = this.files[0] ? this.files[0].name : '선택된 파일 없음';
    document.getElementById('file-name2').textContent = fileName;
});


// 대표 사진
document.getElementById('image-upload3').addEventListener('change', function () {
    var fileName = this.files[0] ? this.files[0].name : '선택된 파일 없음';
    document.getElementById('file-name4').textContent = fileName;
});

// 대표사진2
document.getElementById('image-upload4').addEventListener('change', function () {
    var fileName = this.files[0] ? this.files[0].name : '선택된 파일 없음';
    document.getElementById('file-name5').textContent = fileName;
});

// 대표 텍스트 사진
document.getElementById('image-upload5').addEventListener('change', function () {
    var fileName = this.files[0] ? this.files[0].name : '선택된 파일 없음';
    document.getElementById('file-name6').textContent = fileName;
});

// 업로드 버튼 클릭 이벤트
document.getElementById('upload-btn').addEventListener('click', function () {
    var uploadBtn = this;
    var fileInput = document.getElementById('file-upload');
    if (fileInput.files.length > 0) {
        var file = fileInput.files[0];

        uploadBtn.textContent = '업로드 중 ...';
        uploadBtn.classList.add('shimmering');
        uploadFile(file, uploadBtn);
    } else {
        alert('파일을 선택하세요');
    }
});

function uploadFile(file, uploadBtn) {
    var formData = new FormData();
    formData.append('file', file);

    fetch('http://localhost:7000/upload', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .then(data => {
            alert('영상 업로드가 완료되었습니다.');
            console.log(data);
            uploadBtn.textContent = '업로드 완료';
            uploadBtn.classList.remove('shimmering');
            uploadBtn.classList.add('upload-complete');
        })
        .catch(error => {
            alert('File upload failed');
            console.error('There was a problem with the fetch operation:', error);
        });
}


// 현재 연도 구하기
var currentYear = new Date().getFullYear();
// select 요소 가져오기
var selectElement = document.getElementById('productYear');

// 옵션 추가: 1900년부터 현재 연도까지
for (var year = currentYear; year >= 1900; year--) {
    var option = document.createElement('option');
    option.value = year;
    option.textContent = year;
    selectElement.appendChild(option);
}

// 사진 미리보기
function previewImage(inputId, imgId) {
    document.getElementById(inputId).addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                const previewImg = document.getElementById(imgId);
                previewImg.src = e.target.result;
                previewImg.style.display = 'block';
            }
            reader.readAsDataURL(file);
        }
    });
}

//영상 파일 이름 추가
document.getElementById('file-upload').addEventListener('change', function () {
    var fileName = this.files[0] ? this.files[0].name : '선택된 파일 없음';
    document.getElementById('file-name1').textContent = fileName;
    document.getElementById('file-name-input').value = fileName;
});



previewImage('image-upload1', 'preview-img1');
previewImage('image-upload2', 'preview-img2');
previewImage('image-upload3', 'preview-img3');
previewImage('image-upload4', 'preview-img4');
previewImage('image-upload5', 'preview-img5');

