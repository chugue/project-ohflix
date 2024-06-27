
// 영화 검색
$(document).ready(function () {
    $('.search-input').on('keyup', function () {
        var searchText = $(this).val().toLowerCase();
        if (searchText !== '') {
            $.ajax({
                url: '/api/search',
                type: 'GET',
                data: { query: searchText },
                success: function (data) {
                    var searchResults = $('#search-section .item-list');
                    searchResults.empty();
                    data.forEach(function (item) {
                        var itemHTML = `
                            <div class="item" data-title="${item.title}">
                                <img src="${item.thumbnail}" alt="${item.title}">
                            </div>
                        `;
                        searchResults.append(itemHTML);
                    });
                    $('#search-section').show();
                    $('.category').hide();
                    $('.movie').hide();
                }
            });
        } else {
            $('#search-section').hide();
            $('.category').show();
            $('.movie').show();
        }
    });
});



// ▶️⏯️영상 재생 로직
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
    // const videoUrl = "http://localhost:7000/videos?filename=" + video.getAttribute('data-video-url');
    const videoUrl = "https://ohflix-bucket.s3.ap-northeast-2.amazonaws.com/videolocation/oppenheimer/oppenheimer.mpd";

    console.log(videoUrl)
    // Load the video
    loadVideo(videoUrl);
}

function loadVideo(videoUrl) {
    const player = window.player;
    console.log("👉👉👉👉👉👉👉👉" + videoUrl)

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
