//TODO !!! think of ways to split this code for each html page individually

let firstModalLaunch = true;
let timer = null;
let searchWord = '';
let foodType = 'basic';

$('#modalAddFoodToDiary').on('show.bs.modal', function (e) {
    if (firstModalLaunch) {
        loadAllCustomFoods();
        firstModalLaunch = false;
    }
});

$('#create-food-modal-input').on('keyup', function () {

    let searchText = $(this).val();
    foodType = 'basic';
    loadFoodsContainingKeyWord(searchText);
});

$('#add-food-modal-input').on('keyup', function () {

    let searchText = $(this).val();
    foodType = 'custom';
    $('#food-general-data-table').hide();

    if (searchText.length === 0) {
        loadAllCustomFoods();
        return;
    }

    loadFoodsContainingKeyWord(searchText);
});

const loadAllCustomFoods = function () {
    searchLoader.show();
    foodType = 'custom';
    fetch(URLS.searchCustomFoods)
        .then(handleResponse)
        .then(displayFoodsList)
        .catch(handleError);
};

const loadFoodsContainingKeyWord = function (searchText) {
    if (searchText.length > 2) {
        searchWord = searchText;
        clearTimeout(timer);
        timer = setTimeout(searchFoods, 500);
    }
};

function searchFoods() {
    searchLoader.show();
    fetch(URLS.searchFoods + '?searchWord=' + searchWord + '&foodType=' + foodType)
        .then(handleResponse)
        .then(displayFoodsList)
        .catch(handleError);
}

const displayFoodsList = function (foods) {
    let $foodsList;

    if (foodType === 'basic') {
        $foodsList = $('#create-food-modal-foods-list');
    } else if (foodType === 'custom') {
        $foodsList = $('#add-food-modal-foods-list');
    }

    $foodsList.html('');

    if (foods.length === 0) {
        $foodsList.append('<h4>No results found</h4>');
        searchLoader.hide();
        return;
    }

    let columns = '';
    foods.forEach(food => {
        columns += `<tr><td class="clickable">${food.name}</td></tr>`
    });

    $foodsList.append(columns);
    setClickEvent();
    searchLoader.hide();
};

const setClickEvent = function () {

    let $clickable = $('.clickable');

    $clickable.on('click', function () {
        let $this = $(this);
        $clickable.removeClass('selected');
        $this.addClass('selected');
        $('#food-general-data-table').show();

        let foodName = $(this).text();

        if (foodType === 'basic') {
            fetch(URLS.retrieveFoodNutrition + '?foodName=' + foodName)
                .then(handleResponse)
                .then(setCreateFoodNutrients)
                .catch(handleError);
        } else if (foodType === 'custom') {
            fetch(URLS.retrieveFoodGeneralData + '?foodName=' + foodName)
                .then(handleResponse)
                .then(displayFoodGeneralData)
                .catch(handleError);
        }
    });
};

const setCreateFoodNutrients = function (food) {
    $('#food-calories').val(food.calories);
    $('#food-fat').val(food.fat);
    $('#food-carbohydrates').val(food.carbohydrates);
    $('#food-protein').val(food.protein);
    $('#food-sugars').val(food.sugars);
    $('#food-fiber').val(food.fiber);
    $('#food-cholesterol').val(food.cholesterol);
    $('#food-fat-saturated').val(food.saturatedFats);
    $('#food-calcium').val(food.calcium);
    $('#food-iron').val(food.iron);
    $('#food-potassium').val(food.potassium);
    $('#food-magnesium').val(food.magnesium);
    $('#food-vitamin-a').val(food.vitaminA);
    $('#food-vitamin-c').val(food.vitaminC);
    $('#food-vitamin-b12').val(food.vitaminB12);
    $('#food-vitamin-e').val(food.vitaminE);
    $('#food-added-sugar').val(food.addedSugar);
    $('#food-fat-omega3').val(food.omega3);
    $('#food-fat-omega6').val(food.omega6);
    $('#food-fat-trans').val(food.transFats);
    $('#food-phosphorus').val(food.phosphorus);
    $('#food-sodium').val(food.sodium);
    $('#food-zinc').val(food.zinc);
    $('#food-copper').val(food.copper);
    $('#food-manganese').val(food.manganese);
    $('#food-selenium').val(food.selenium);
    $('#food-vitamin-b1').val(food.vitaminB1);
    $('#food-vitamin-b2').val(food.vitaminB2);
    $('#food-vitamin-b3').val(food.vitaminB3);
    $('#food-vitamin-b5').val(food.vitaminB5);
    $('#food-vitamin-b6').val(food.vitaminB6);
    $('#food-vitamin-b9').val(food.vitaminB9);
    $('#food-vitamin-d').val(food.vitaminD);
    $('#food-vitamin-k').val(food.vitaminK);
    $('#food-fat-monounsaturated').val(food.monounsaturatedFats);
    $('#food-fat-polyunsaturated').val(food.polyunsaturatedFats);
};

const displayFoodGeneralData = function ({calories, fat, carbohydrates, protein, price, productWeight}) {

    $('#food-general-calories').text(calories);
    $('#food-general-fat').text(roundToTwoDecimalPlaces(fat));
    $('#food-general-carbs').text(roundToTwoDecimalPlaces(carbohydrates));
    $('#food-general-protein').text(roundToTwoDecimalPlaces(protein));
    $('#food-general-price').text(roundToTwoDecimalPlaces(price));
    $('#serving-size').val(productWeight);
};

const roundToTwoDecimalPlaces = function (num) {
    return (Math.round((num + Number.EPSILON) * 100) / 100).toFixed(2);
};


