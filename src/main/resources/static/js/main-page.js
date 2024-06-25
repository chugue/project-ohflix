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


// 영상 재생 로직
document.addEventListener('DOMContentLoaded', initApp);

function initApp() {
    // Install built-in polyfills to patch browser incompatibilities.
    shaka.polyfill.installAll();

    // Check if the browser supports the basic APIs Shaka needs.
    if (shaka.Player.isBrowserSupported()) {
        // Everything looks good!
        initPlayer();
    } else {
        // This browser does not have the minimum set of APIs we need.
        console.error('Browser not supported!');
    }
}

function initPlayer() {
    // Create a Player instance.
    const video = document.getElementById('videoPlayer');
    const player = new shaka.Player(video);

    // Attach player to the window to make it easy to access in the JS console.
    window.player = player;

    // Listen for error events.
    player.addEventListener('error', onErrorEvent);

    // 비디오 URL 설정
    const videoFilename = video.getAttribute('data-video-url');

    // 비디오 URL 설정
    fetchVideoUrl(videoFilename);

    // 특정 구간 재생 설정
    video.addEventListener('loadedmetadata', function() {
        const startTime = 10; // 재생 시작 시간 (초)
        const endTime = 180;   // 재생 종료 시간 (초)

        video.currentTime = startTime;

        video.addEventListener('timeupdate', function() {
            if (video.currentTime >= endTime) {
                video.pause();
                video.classList.add('fade-out');
                video.currentTime = 0;
            }
        });

        video.play();
    });

}
// jQuery AJAX 요청을 통해 비디오 URL을 가져오는 함수
function fetchVideoUrl(filename) {
    $.ajax({
        url: 'http://localhost:7000/videos?filename=' + encodeURIComponent(filename),
        method: 'GET',
        dataType: 'text', // Ensure that the response is interpreted as text
        success: function(response) {
            console.log("AJAX response received");
            console.log(response); // 응답 내용을 출력하여 확인

            try {
                const parser = new DOMParser();
                const xmlDoc = parser.parseFromString(response, "application/xml");
                if (xmlDoc.getElementsByTagName('MPD').length == 0) {
                    throw new Error("Invalid MPD XML");
                }

            const videoBlob = new Blob([response], { type: 'application/dash+xml' });
            const videoUrl = "http://localhost:7000/videolocation/spiderman/spiderman.mpd";
            console.log("Blob URL: " + videoUrl);
            loadVideo(videoUrl); // loadVideo 함수 호출
            } catch (e) {
                onError({ code: 7001, message: e.message });
            }
        },
        error: function(xhr, status, error) {
            onError('Failed to fetch video URL: ' + error);
        }
    });
}


function loadVideo(videoUrl) {
    const player = window.player;

    console.log("Loading video from URL: " + videoUrl);

    // Try to load a manifest.
    // This is an asynchronous process.
    try {
        player.load(videoUrl).then(function () {
            // This runs if the asynchronous load is successful.
            console.log('The video has now been loaded!');
        }).catch(onError);  // onError is executed if the asynchronous load fails.
    } catch (e) {
        onError(e);
    }
}




function onErrorEvent(event) {
    // Extract the shaka.util.Error object from the event.
    onError(event.detail);
}

function onError(error) {
    // Log the error.
    console.error('Error code', error.code, 'object', error);
    if (error.code === 7002) {
        console.error('Manifest loading error. Please check the manifest URL and CORS settings.');
    }
}
