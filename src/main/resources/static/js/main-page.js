// 영화 검색
$(document).ready(function () {
    // input.earche-input 값 저장
    $('.search-input').on('keyup', function () {
        var searchText = $(this).val().toLowerCase();
        // div.scroll img 에서 해당 값 검색
        $('.item').each(function () {
            var title = $(this).data('title') ? $(this).data('title').toLowerCase() : "";
            var genre = $(this).data('genre') ? $(this).data('genre').toLowerCase() : "";
            // 있으면 show, 없으면 hide
            if (title.includes(searchText) || genre.includes(searchText)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });

        // 'searchText'가 ''이면 숨기기, 아니면 텍스트와 표시
        if (searchText !== '') {
            $('#search-section').show();
            $('#search-text').text(searchText).show();
            $('.category').hide();
            $('.movie').hide();
        } else {
            $('#search-section').hide();
            $('.category').show();
            $('.movie').show();
        }
    });
});
