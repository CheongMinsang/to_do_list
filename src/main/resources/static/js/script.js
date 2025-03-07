function selectColor(element, id, label) {
    document.getElementById(id).checked = true;
    document.querySelectorAll('.color-option').forEach(el => el.classList.remove('selected'));
    element.classList.add('selected');
    document.getElementById('colorDropdownButton').innerText = label || '색상 선택';
}

function selectIcon(element, id, label) {
    document.getElementById(id).checked = true;
    document.querySelectorAll('.icon-option').forEach(el => el.classList.remove('selected'));
    element.classList.add('selected');
    document.getElementById('iconDropdownButton').innerText = label || '아이콘 선택';
}