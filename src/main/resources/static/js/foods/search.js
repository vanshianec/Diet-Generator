//TODO !!! think of ways to split this code for each html page individually

let firstModalLaunch = true;
let timer = null;
let searchWord = '';
let foodId;
let foodType = 'basic';
let foodFatPerHundredGrams;
let foodCarbsPerHundredGrams;
let foodProteinPerHundredGrams;
let foodCaloriesPerHundredGrams;
let foodPricePerServing;
let foodServingSize;

//TODO see correct camel case for id naming

$('#modal-add-food-to-diary').on('show.bs.modal', function (e) {
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
    fetch(URLS.getAllCustomFoods)
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
        columns += `<tr><td data-id=${food.id} class="clickable">${food.name}, ${food.purchasePlace}</td></tr>`
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
        foodId = $(this).data("id");

        if (foodType === 'basic') {
            fetch(URLS.retrieveFoodNutrition + '?foodId=' + foodId)
                .then(handleResponse)
                .then(setCreateFoodNutrients)
                .catch(handleError);
        } else if (foodType === 'custom') {
            fetch(URLS.retrieveFoodGeneralData + '?foodId=' + foodId)
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
    foodCaloriesPerHundredGrams = calories;
    foodFatPerHundredGrams = fat;
    foodCarbsPerHundredGrams = carbohydrates;
    foodProteinPerHundredGrams = protein;
    foodPricePerServing = price;
    foodServingSize = productWeight;

    updateDisplayData(productWeight);
};

const convertDataPerServing = function (data, servingSize, defaultServingSize) {

    if (defaultServingSize === undefined) {
        defaultServingSize = 100;
    }

    return (servingSize / defaultServingSize) * data;
};

$('#serving-size').on('keyup', function () {
    let servingSize = $(this).val();
    updateDisplayData(servingSize);
});

const updateDisplayData = function (servingSize) {
    let $calories = $('#food-general-calories');
    let $fat = $('#food-general-fat');
    let $carbs = $('#food-general-carbs');
    let $protein = $('#food-general-protein');
    let $price = $('#food-general-price');

    let caloriesPerServing = parseInt(convertDataPerServing(foodCaloriesPerHundredGrams, servingSize, 100));
    let fatPerServing = convertDataPerServing(foodFatPerHundredGrams, servingSize, 100);
    let carbsPerServing = convertDataPerServing(foodCarbsPerHundredGrams, servingSize, 100);
    let proteinPerServing = convertDataPerServing(foodProteinPerHundredGrams, servingSize, 100);
    let pricePerServing = convertDataPerServing(foodPricePerServing, servingSize, foodServingSize);

    let roundedFat = fatPerServing.toFixed(1);
    let roundedCarbs = carbsPerServing.toFixed(1);
    let roundedProtein = proteinPerServing.toFixed(1);
    let roundedPrice = pricePerServing.toFixed(2);

    $calories.text(caloriesPerServing);
    $fat.text(roundedFat);
    $carbs.text(roundedCarbs);
    $protein.text(roundedProtein);
    $price.text(roundedPrice);
    $('#serving-size').val(servingSize);

    addFoodToDiaryChart.data.datasets[0].data[0] = proteinPerServing;
    addFoodToDiaryChart.data.datasets[0].data[1] = carbsPerServing;
    addFoodToDiaryChart.data.datasets[0].data[2] = fatPerServing;

    addFoodToDiaryChart.update();
};