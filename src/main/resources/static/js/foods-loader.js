$(document).ready(function () {
    loadFoods();
});

$(window).scroll(function () {
    /* if the bottom of the page is reached */
    if ($(window).scrollTop() === $(document).height() - $(window).height()) {

    }
});

const loadFoods = function () {
    loader.show();
    fetch(URLS.foods)
        .then(response => response.json())
        .then(foods => {
            let result = '';
            foods.forEach(food => {
                const itemString = toString(food);
                result += itemString;
            });
            $('#foods-columns').html(result);
            loader.hide();
        });

    //TODO catch and test no network case
};

const toString = function ({name, foodGroup, calories, fat, carbohydrates, protein}) {
    let columns = `<td>${name}</td>
                   <td>${foodGroup}</td>
                   <td>${calories}</td>
                   <td>${fat}</td>
                   <td>${carbohydrates}</td>
                   <td>${protein}</td>`;

    return `<tr>${columns}</tr>`
};

const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },
};

const URLS = {
    foods: '/api/foods',
};
