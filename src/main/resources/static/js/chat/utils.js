/* Util Function */
/* Util Function */
async function getData(url='') {
    const response = await fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return response.json();
}

async function postData(url = '', data = {}){
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
    return response.json();
}

import EmojiButton from '@joeattardi/emoji-button';

window.addEventListener('DOMContentLoaded', () => {
    const button = document.querySelector('#emoji-button');
    const picker = new EmojiButton();

    picker.on('emoji', emoji => {
        document.querySelector('#message-send').value += emoji;
    });

    button.addEventListener('click', () => {
        picker.togglePicker(button);
    });
});
