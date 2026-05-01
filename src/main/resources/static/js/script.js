document.addEventListener('DOMContentLoaded', function () {
    const display = document.getElementById('display');
    const allButtons = document.querySelectorAll('[data-value]');
    const delBtn = document.querySelector('.button-12');
    const resetBtn = document.querySelector('.button-42');
    const equalBtn = document.querySelector('.button-44');
    const form = document.getElementById('calculatorForm');

    // Флаг: нужно ли очистить дисплей перед вводом
    // При загрузке страницы проверяем, нужно ли сбросить флаг
    let needReset = false;
    const needResetFlag = document.getElementById('needResetFlag');
    if (needResetFlag && needResetFlag.value === 'true') {
        needReset = true;
    }

    // console.log('Обычных кнопок (с data-value):', allButtons.length);
    // console.log('DEL найден:', delBtn);
    // console.log('RESET найден:', resetBtn);
    // console.log('= найден:', equalBtn);


    // Функция для вставки значения с учетом сброса
    function insertValue(value) {
        if (needReset) {
            display.value = '';     // очищаем дисплей
            needReset = false;      // сбрасываем флаг
        }
        display.value += value;
    }

    // Обработчик для обычных кнопок (с data-value)
    allButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            const value = button.getAttribute('data-value');
            console.log('Нажата кнопка со значением:', value);
            insertValue(value);  // используем функцию со сбросом
        });
    });

    // Обработчик для DEL (удаление - не требует сброса)
    if (delBtn) {
        delBtn.addEventListener('click', function () {
            console.log('DEL нажат - удаляем');
            if (needReset) {
                display.value = '';     // очищаем дисплей
                needReset = false;      // сбрасываем флаг
            }
            display.value = display.value.slice(0, -1);
        });
    }

    // Обработчик для RESET (полная очистка)
    if (resetBtn) {
        resetBtn.addEventListener('click', function () {
            console.log('RESET нажат - очищаем');
            needReset = false;   // сбрасываем флаг
            display.value = '';
        });
    }

    // Обработка отправки формы (когда нажали =)
    if (form) {
        form.addEventListener('submit', function(event) {
            needReset = true;
        });
    }
});