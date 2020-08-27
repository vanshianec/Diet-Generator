let areFoodsLoaded = false;

$(document).ready(function () {
    loadFoods();
});

$(window).scroll(function () {
    /* if the bottom of the page is reached */
    if ($(window).scrollTop() === $(document).height() - $(window).height()) {
        loadFoods();
    }
});

const loadFoods = function () {
    if (!areFoodsLoaded) {
        loader.show();
        fetch(URLS.foods)
            .then(handleResponse)
            .then(addFoodsToTemplate)
            .catch(handleError);
        loader.hide();
    }
};

const handleResponse = function (response) {

    if (!response.ok) {
        throw new Error('Network response was not ok');
    }

    return response.json();
};

const handleError = function (error) {
    //TODO do some error handling
    console.error(error);
};

const addFoodsToTemplate = function (foods) {
    if (foods.length === 0) {
        areFoodsLoaded = true;
        //TODO add end of page html
    }

    let result = '';
    foods.forEach(food => {
        const itemString = toString(food);
        result += itemString;
    });
    $('#foods-columns').append(result);
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
