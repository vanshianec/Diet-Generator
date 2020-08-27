let areFoodsLoaded = false;
let pageCount = 0;

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

$(window).on('load', function () {
    pageCount = 0;
    areFoodsLoaded = false;
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
        fetch(URLS.foods + '?page=' + pageCount)
            .then(handleResponse)
            .then(addFoodsToTemplate)
            .catch(handleError);
    }
};

const handleResponse = function (response) {

    if (!response.ok) {
        throw new Error('Network response was not ok');
    }

    return response.json();
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
    pageCount++;
    loader.hide();
};

const handleError = function (error) {
    //TODO do some error handling
    console.error(error);
    loader.hide();
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
