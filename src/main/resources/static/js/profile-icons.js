document.querySelectorAll('.profile-item img').forEach(item => {
  item.addEventListener('click', () => {
    const profilePic = document.getElementById('currentProfilePic');
    profilePic.src = item.src;
  });
});

document.querySelector('.back-button').addEventListener('click', () => {
  window.history.back();
});

const containers = [
  { containerId: 'profileContainer1', leftButtonId: 'scrollLeft1', rightButtonId: 'scrollRight1' },
  { containerId: 'profileContainer2', leftButtonId: 'scrollLeft2', rightButtonId: 'scrollRight2' },
  { containerId: 'profileContainer3', leftButtonId: 'scrollLeft3', rightButtonId: 'scrollRight3' },
  { containerId: 'profileContainer4', leftButtonId: 'scrollLeft4', rightButtonId: 'scrollRight4' },
];

containers.forEach(({ containerId, leftButtonId, rightButtonId }) => {
  const container = document.getElementById(containerId);
  const leftButton = document.getElementById(leftButtonId);
  const rightButton = document.getElementById(rightButtonId);
  const items = container.querySelectorAll('.profile-item').length;

  if (items > 6) {
    leftButton.style.display = 'block';
    rightButton.style.display = 'block';
  }

  rightButton.addEventListener('click', () => {
    container.scrollBy({ left: 220, behavior: 'smooth' }); // Increase scroll distance
  });

  leftButton.addEventListener('click', () => {
    container.scrollBy({ left: -220, behavior: 'smooth' }); // Increase scroll distance
  });
});
