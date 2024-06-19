// document.querySelector('.fa-solid').addEventListener('click', function () {
//     const searchInput = document.querySelector('.search-input');
//     if (searchInput.classList.contains('show')) {
//         searchInput.classList.remove('show');
//     } else {
//         searchInput.classList.add('show');
//         searchInput.focus();
//     }
// });

// 영화 검색
$(document).ready(function () {
    // input.earche-input 값 저장
    $('.search-input').on('keyup', function () {
        var searchText = $(this).val().toLowerCase();
        // div.scroll img 에서 해당 값 검색
        $('.scroll img').each(function () {
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
            $('.search-section').show();
            $('#search-text').text(searchText).show();
            $('.category').hide();
            $('.movie').hide();
        } else {
            $('.search-section').hide();
            $('.category').show();
            $('.movie').show();
        }
    });
});

// 이미지 슬라이드
$(document).ready(function () {
    var pageIndex = 0;
    var itemsPerPage = 5; // 한 페이지 보일 아이템 수
    var totalItems = $('#top10-movie img').length;

    // 초기 상태
    showItems(pageIndex);

    // 다음, 이전 넘기기 버튼
    $('#next5btn').on('click', function () {
        pageIndex = (pageIndex + itemsPerPage) % totalItems;
        showItems(pageIndex);
    });
    $('#prev5btn').on('click', function () {
        pageIndex = (pageIndex - itemsPerPage + totalItems) % totalItems;
        if (pageIndex < 0) {
            pageIndex += totalItems;
        }
        showItems(pageIndex);
    });

    // 이미지 보여주기 function
    function showItems(startIndex) {
        var $items = $('#top10-movie img');
        $items.fadeOut(); // 모든 이미지 숨기기

        // startIndex부터 itemsPerPage 만큼의 이미지를 보여주기
        for (var i = startIndex; i < startIndex + itemsPerPage; i++) {
            $items.eq(i % totalItems).fadeIn();
        }
        // 버튼의 활성 상태 설정
        // $('#prev5btn').toggle(startIndex > 0);
    }
});

// 이미지 슬라이드
// $(document).ready(function () {
//     var $prevBtn = $('#prev5btn');
//     var totalImages = $('#top10-movie img').length;
//     var itemsPerPage = 5; // 한 페이지 아이템 수
//     let pageIndex = 0;
//
//     // item list 보여주기
//     function showItems() {
//         const offset = -pageIndex * (100 / itemsPerPage);
//         $('#top10-movie').css('transform', `translateX(${offset}%)`);
//     }
//     // 다음 5개 item
//     $('#next5btn').on('click', function(){
//         pageIndex = (pageIndex + itemsPerPage) % totalImages;
//         showItems();
//     });
//     // 이전 5개 item
//     $('#prev5btn').on('click', function(){
//         pageIndex = (pageIndex - itemsPerPage + totalImages) % totalImages;
//         showItems();
//     });
//     // 초기화 TODO : 필요한가?
//     // showItems();
// });