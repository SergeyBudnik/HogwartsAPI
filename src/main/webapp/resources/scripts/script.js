function sendRequest(eventId) {
    var buttonElement = document.getElementById('send');

    buttonElement.innerText = 'Заявка отправляется...';
    buttonElement.onclick = null;

    var name = document.getElementById('name').value;
    var telephone = document.getElementById('phone').value;

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'https://hogwarts-engschool.ru:8443/HogwartsAPI/public/events/' + eventId + '/participant', false);
    xhr.setRequestHeader('Content-type', 'application/json');

    xhr.send(JSON.stringify({
        name: name,
        phone: telephone,
        referralSource: 'VK_SEARCH'
    }));

    buttonElement.innerText = 'Заявка отправлена';
}

function signUpForFreeLesson() {
    var buttonElement = document.getElementById('send');

    buttonElement.innerText = 'Заявка отправляется...';
    buttonElement.onclick = null;

    var name = document.getElementById('name').value;
    var telephone = document.getElementById('phone').value;

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'https://hogwarts-engschool.ru:8443/HogwartsAPI/public/user-requests', false);
    xhr.setRequestHeader('Content-type', 'application/json');

    xhr.send(JSON.stringify({
        name: name,
        phone: telephone
    }));

    buttonElement.innerText = 'Заявка отправлена';
}