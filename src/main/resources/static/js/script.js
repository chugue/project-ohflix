$(document).ready(function() {
    // 프로필 사진 변경 기능
    $('#profile-pic-upload').change(function(event) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#profile-pic').attr('src', e.target.result);
        }
        reader.readAsDataURL(event.target.files[0]);
    });
});
