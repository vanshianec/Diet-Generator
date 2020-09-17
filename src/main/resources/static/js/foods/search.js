let timer = null;
let searchWord = '';

$('#search-food').on('keyup', function () {

    let searchText = $(this).val();

    if (searchText.length > 2) {
        searchWord = searchText;
        clearTimeout(timer);
        timer = setTimeout(searchFoods, 500);
    }
});

function searchFoods() {
    searchLoader.show();
    fetch(URLS.searchFoods + '?searchWord=' + searchWord)
        .then(handleResponse)
        .then(displayFoodsList)
        .catch(handleError);
}

const displayFoodsList = function (foods) {
    let $foodsList = $('#foods-list');
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

        let foodName = $(this).text();
        fetch(URLS.retrieveFoodNutrition + '?foodName=' + foodName)
            .then(handleResponse)
            .then(setCreateFoodNutrients)
            .catch(handleError);
    });
};

const setCreateFoodNutrients = function(food){
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

