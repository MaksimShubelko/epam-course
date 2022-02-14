document.addEventListener('change', saveIt);
document.addEventListener('DOMContentLoaded', loadValues);
const prefix = 'mySave_';

function saveIt(evt) {
    const elem = evt.target;
    if (evt.target.matches('input[type="text"], select')) {
        const name = elem.getAttribute('id') || elem.getAttribute('name');
        if (name) {
            localStorage.setItem(prefix + name, elem.value);
        }
    }
}

function loadValues() {
    for (let key in { ...localStorage }) {
        if (key.indexOf(prefix) === 0) {
            const domKey = key.replace(prefix, '');
            const elem = document.getElementById(domKey)
                || document.querySelector(`[name="${domKey}"]`);
            if (elem) {
                elem.value = localStorage.getItem(key);
            }
        }
    }
}