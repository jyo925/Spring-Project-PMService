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


var finder = document.querySelector("#finder"),
    list   = document.querySelector("#search-list"),
    lib;

finder.addEventListener("keyup", onKeydown);

// basic usage
function onKeydown(e) {
    if(!e.target.value) { list.innerHTML = ''; return; }
    var results = FS.search(e.target.value, lib);
    if(results.success) {
        outputSearchResults(results);
        console.log(results);
    } else {
        console.error(results);
    }
}



// handling search results
function outputSearchResults(results) {
    // clear list
    list.innerHTML = '';

    // you deleted the last letter, do nothing more
    if(results.count === lib.length) return;

    // label exact
    if(results.exact.length) label(list, 'Exact Matches');
    // spit out exacts
    outputMatches(results.exact);

    // no need to go further unless fuzzy
    if(!results.fuzzy.length) return;

    // label fuzzy
    if(results.fuzzy.length) label(list, 'Fuzzy Matches');
    // spit out fuzzies
    outputMatches(results.fuzzy);
}

// outputting matches
function outputMatches(matchesArray) {
    matchesArray.forEach((match) => {
        var el = document.createElement('li');
        match._substrings.forEach((str) => {
            if(str.match) {
                el.innerHTML += `<strong>${str.str}</strong>`;
            } else {
                el.innerHTML += str.str;
            }
        });
        list.appendChild(el);
    });
}

// labeling output
function label(list, text) {
    var line = document.createElement('li');
    line.innerHTML = text;
    line.className = 'label';
    list.appendChild(line);
}