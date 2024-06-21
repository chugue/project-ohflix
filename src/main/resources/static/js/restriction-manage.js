document.addEventListener('DOMContentLoaded', function() {
    const slider = document.getElementById('ageRange');
    const ageLabel = document.getElementById('ageLabel');
    const labels = ['전체관람가', '15세이상', '19세이상'];

    fetch('user-restriction-info')
        .then(response => response.json())
        .then(data => {
            console.log(data);
            const restrictionLevel = data.response.userSaveRate;
            let index = labels.indexOf(mapRateToLabel(restrictionLevel));
            if (index !== -1){
                slider.value = index;
                updateAgeLabel(slider, ageLabel,labels);
                updateSliderBackground(slider)
            }
        } )
        .catch(error => console.error("사용자의 관람등급을 가져오는데 실패했습니다."));

    slider.addEventListener('input', function() {
        updateSliderBackground(slider);
        updateAgeLabel(slider, ageLabel, labels);
    });

    updateSliderBackground(slider); // 초기값 설정
});

function updateSliderBackground(slider) {
    const value = (slider.value - slider.min) / (slider.max - slider.min) * 100;
    const color = '#4CAF50';
    slider.style.background = `linear-gradient(to right, ${color} 0%, ${color} ${value}%, #ddd ${value}%, #ddd 100%)`;
}


function updateAgeLabel(slider, ageLabel, labels) {
    const index = parseInt(slider.value, 10);
    ageLabel.textContent = labels[index];
}

function mapRateToLabel(rate){
    switch (rate) {
        case 'ALL':
            return '전체관람가';
        case 'PG':
            return '15세이상';
        case 'R':
            return '19세이상';
        default:
            return '전체관람가';
    }
}
