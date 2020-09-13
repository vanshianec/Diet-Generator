let $chooseFoodCategory = $('#choose-food-category');
let $chooseFoodGroup = $('#choose-food-group');
let $chooseAdditionalNutrient = $('#choose-additional-nutrient');

let sortType = 'none';
let foodCategory = $chooseFoodCategory.val();
let foodGroup = $chooseFoodGroup.val();
let additionalNutrient = $chooseAdditionalNutrient.val();
let sortParam = '';
let areAllFoodsLoaded = false;
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

const loadFoods = function (cancelReset) {

    if (!cancelReset) {
        resetFoodsData();
    }

    let url = URLS.foods + '?page=' + pageCount
        + '&foodCategory=' + foodCategory
        + '&additionalNutrient=' + additionalNutrient;

    if (foodGroup !== 'none') {
        url += '&foodGroup=' + foodGroup;
    }

    if (sortType !== 'none') {
        url += '&sort=' + sortParam + ',' + sortType;
    }

    if (!areAllFoodsLoaded) {
        loader.show();
        fetch(url)
            .then(handleResponse)
            .then(addFoodsToTemplate)
            .catch(handleError);
    }
};

const resetFoodsData = function () {
    pageCount = 0;
    areAllFoodsLoaded = false;
    $('#foods-columns').html('');
};

const handleResponse = function (response) {

    if (!response.ok) {
        throw new Error('Network response was not ok');
    }

    return response.json();
};

const addFoodsToTemplate = function (foods) {
    if (foods.length === 0) {
        areAllFoodsLoaded = true;
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

const toString = function ({name, calories, fat, carbohydrates, protein, additionalNutrient}) {

    let columns = `<td>${name}</td>
                   <td>${calories}</td>
                   <td>${carbohydrates}</td>
                   <td>${fat}</td>
                   <td>${protein}</td>
                   <td>${additionalNutrient}</td>`;

    return `<tr>${columns}</tr>`;
};

const handleError = function (error) {
    //TODO do some error handling
    loader.hide();
};

$(window).on('load', function () {
    loadFoods(true);
});

$(window).scroll(function () {
    /* if the bottom of the page is reached */
    if ($(window).scrollTop() === $(document).height() - $(window).height()) {
        loadFoods(true);
    }
});

let $sortable = $('.sortable');

$sortable.on('click', function () {

    let $this = $(this);
    let asc = $this.hasClass('asc');
    let desc = $this.hasClass('desc');
    $sortable.removeClass('asc').removeClass('desc');
    if (!asc && !desc) {
        $this.addClass('asc');
    } else if (asc) {
        $this.addClass('desc');
    }

    asc = $this.hasClass('asc');
    desc = $this.hasClass('desc');

    if ($this.hasClass('additional-nutrient')) {
        sortParam = $('#choose-additional-nutrient option:selected').val();
    } else {
        sortParam = toCamelCase($this.text());
    }

    if (asc) {
        sortType = 'asc';
    } else if (desc) {
        sortType = 'desc';
    } else {
        sortType = 'none';
    }

    loadFoods();
});

const toCamelCase = function (str) {
    return str.replace(/(?:^\w|[A-Z]|\b\w)/g, function (word, index) {
        return index === 0 ? word.toLowerCase() : word.toUpperCase();
    }).replace(/\s+|-/g, '');
};

$chooseFoodGroup.on('change', function () {
    foodGroup = $(this).val();
    loadFoods();
});

$chooseFoodCategory.on('change', function () {
    foodGroup = $(this).val();
    loadFoods();
});

$chooseAdditionalNutrient.on('change', function () {
    additionalNutrient = $(this).val();
    let selectedNutrientDisplayText = $('#choose-additional-nutrient option:selected').text();
    let additionalNutrientField = $('#additional-nutrient-column');
    additionalNutrientField.text(selectedNutrientDisplayText);

    if (additionalNutrientField.hasClass('asc') || additionalNutrientField.hasClass('desc')) {
        /* use additional nutrient as view for the table and for parameter to sort by if sorting is required */
        sortParam = additionalNutrient;
    }

    loadFoods();
});

