document.addEventListener('DOMContentLoaded', function() {
    const slider = document.getElementById('ageRange');

    slider.addEventListener('input', function() {
        updateSliderBackground(slider);
    });

    updateSliderBackground(slider); // 초기값 설정
});

function updateSliderBackground(slider) {
    const value = (slider.value - slider.min) / (slider.max - slider.min) * 100;
    const color = '#4CAF50';
    slider.style.background = `linear-gradient(to right, ${color} 0%, ${color} ${value}%, #ddd ${value}%, #ddd 100%)`;
}
