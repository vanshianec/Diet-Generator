const maxNutrientsDefaultValues = {
    calories: 2100,
    saturatedFats: null,
    cholesterol: 300,
    vitaminA: 10000,
    vitaminB3: 35,
    vitaminB6: 100,
    vitaminC: 2000,
    vitaminD: 2000,
    vitaminE: 1000,
    vitaminB9: 1000,
    vitaminK: 2000,
    calcium: 2500,
    copper: 10,
    iron: 45,
    manganese: 11,
    phosphorus: 4000,
    selenium: 400,
    zinc: 40,
}

const goalNutrientsDefaultValues = {
    calories: 2000,
    protein: 200,
    carbohydrates: 200,
    fat: 45,
    fiber: 38,
    sugars: null,
    addedSugar: null,
    sodium: null,
    omega3: null,
    omega6: null,
    cholesterol: null,
    saturatedFats: null,
    transFats: null,
    monounsaturatedFats: null,
    polyunsaturatedFats: null,
    vitaminB1: 1.2,
    vitaminB2: 1.3,
    vitaminB3: 16,
    vitaminB5: 5,
    vitaminB6: 1.3,
    vitaminB9: 400,
    vitaminB12: 2.4,
    vitaminA: 3000,
    vitaminC: 90,
    vitaminE: 15,
    vitaminK: 120,
    vitaminD: null,
    calcium: 1000,
    copper: 0.9,
    iron: 8,
    magnesium: 400,
    manganese: 2.3,
    phosphorus: 700,
    potassium: 4700,
    selenium: 55,
    zinc: 11
}

const diaryNutritionDataIds = [
    '#diary-data-calories',
    '#diary-data-protein',
    '#diary-data-carbs',
    '#diary-data-fat',
    '#diary-data-fiber',
    '#diary-data-sugars',
    '#diary-data-added-sugar',
    '#diary-data-sodium',
    '#diary-data-fat-omega3',
    '#diary-data-fat-omega6',
    '#diary-data-cholesterol',
    '#diary-data-fat-saturated',
    '#diary-data-fat-trans-fats',
    '#diary-data-fat-monounsaturated',
    '#diary-data-fat-polyunsaturated',
    '#diary-data-vitamin-b1',
    '#diary-data-vitamin-b2',
    '#diary-data-vitamin-b3',
    '#diary-data-vitamin-b5',
    '#diary-data-vitamin-b6',
    '#diary-data-vitamin-b9',
    '#diary-data-vitamin-b12',
    '#diary-data-vitamin-a',
    '#diary-data-vitamin-c',
    '#diary-data-vitamin-d',
    '#diary-data-vitamin-e',
    '#diary-data-vitamin-k',
    '#diary-data-calcium',
    '#diary-data-copper',
    '#diary-data-iron',
    '#diary-data-magnesium',
    '#diary-data-manganese',
    '#diary-data-phosphorus',
    '#diary-data-potassium',
    '#diary-data-selenium',
    '#diary-data-zinc'
]

let currentFoodsData = new FoodData();
let goalNutrients = new FoodData(goalNutrientsDefaultValues);
let maxNutrients = new FoodData(maxNutrientsDefaultValues);

let foodsInDiaryList = [];


const addFoodToDiary = function () {
    deselectSelectedFood();
    restoreFoodsInDiaryData();

    let servingSize = $('#serving-size').val();
    fetch(URLS.getCustomFood + '?foodId=' + foodId + '&servingSize=' + servingSize)
        .then(handleResponse)
        .then(displayFoodInDiary)
        .catch(handleError);
};

const generateDiet = function () {

    resetCurrentFoodsData();
    clearDiaryTable();
    resetPriceBreakdownChart();
    setGoalNutrients();
    setMaxNutrients();
    foodsInDiaryList = [];

    fetch(URLS.generateDiet, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify([goalNutrients, maxNutrients])
    })
        .then(handleResponse)
        .then(displayGeneratedFoodsInDiary)
        .catch(handleError);
}


$('#add-food-to-diary-button').on('click', addFoodToDiary);

$('#generate-diet-button').on('click', generateDiet);

const resetCurrentFoodsData = function () {
    currentFoodsData.setData(new FoodData());
};

const clearDiaryTable = function () {
    $('#diary-foods-list').html('');
};

const resetPriceBreakdownChart = function () {
    $('#price-breakdown-price-amount').text('0');
    priceBreakdownChart.data.datasets[0].data = [];
    priceBreakdownChart.data.labels = [];
    priceBreakdownChart.data.datasets[0].backgroundColor = [];
    priceBreakdownChart.data.datasets[0].borderColor = [];
    priceBreakdownChart.options.tooltips.callbacks.title = formatTitle;
    priceBreakdownChart.update();
};

const setGoalNutrients = function () {
    goalNutrients.calories = $('#goal-calories-input').val();
    goalNutrients.protein = $('#goal-protein-input').val();
    goalNutrients.carbohydrates = $('#goal-carbs-input').val();
    goalNutrients.fat = $('#goal-fat-input').val();
    goalNutrients.fiber = $('#goal-fiber-input').val();
}

const setMaxNutrients = function () {
    maxNutrients.calories = $('#max-calories-input').val();
    maxNutrients.saturatedFats = (goalNutrients.calories * 0.1) / 9;
}

const deselectSelectedFood = function () {
    const $clickable = $('.clickable-food-diary');
    $clickable.removeClass('table-active');
}

const restoreFoodsInDiaryData = function () {
    resetCurrentFoodsData();
    resetPriceBreakdownChart();
    loadAllFoodsData();
}

const displayGeneratedFoodsInDiary = function (foods) {
    foods.forEach(food => displayFoodInDiary(food));
};

const displayFoodInDiary = function (food) {
    convertFoodDataForDynamicWeight(food);
    foodsInDiaryList.push(food);
    addFoodToDiaryTable(food);
    addFoodDataToCurrentData(food);
    updateCaloriesBreakdownChart();
    addFoodToPriceBreakdownChart(food);
    displayFoodsData();
};

const addFoodToDiaryTable = function (food) {
    let row = `<tr data-id=${foodsInDiaryList.length - 1} class="clickable-food-diary" onclick="showSelectedFoodData(this)">
                         <td>${food.name}, ${food.purchasePlace}</td>
                         <td>${food.dynamicProductWeight} g</td>
                         <td>${food.calories}</td>
                         <td>
                            <div class="row">
                                <span class="col-sm-7">${food.price.toFixed(2)} lv</span>
                                <div style="display: inline-block" class="col-sm-5">
                                <i class="fa fa-trash fa-lg remove-food-icon" data-toggle="tooltip" title="Remove" onclick="removeFoodFromDiary(this, event)"></i>
                                </div>  
                            </div>
                         </td>                              
                    </tr>`;
    $('#diary-foods-list').append(row);
};

const removeFoodFromDiary = function (context, event) {
    const foodRow = $(context).parent().parent().parent().parent();
    const foodIndex = foodRow.data("id");
    foodsInDiaryList.splice(foodIndex, 1);
    foodRow.remove();

    resetFoodsTableIndices();
    deselectSelectedFood();
    restoreFoodsInDiaryData();
    updateCaloriesBreakdownChart();
    displayFoodsData();

    event.preventDefault();
    event.stopPropagation();
}

const resetFoodsTableIndices = function () {
    $('#diary-foods-list > tr').each(function (index) {
        $(this).attr("data-id", index);
    })
}

const showSelectedFoodData = function (context) {
    const $this = $(context);
    const $clickable = $('.clickable-food-diary');

    resetPriceBreakdownChart();

    if ($this.hasClass('table-active')) {
        $clickable.removeClass('table-active');
        resetCurrentFoodsData();
        loadAllFoodsData();

    } else {
        $clickable.removeClass('table-active');
        $this.addClass('table-active');
        loadSelectedFoodData($this);
    }

    updateCaloriesBreakdownChart();
    displayFoodsData();
};

const loadAllFoodsData = function () {
    for (const food of foodsInDiaryList) {
        addFoodDataToCurrentData(food);
        addFoodToPriceBreakdownChart(food);
    }
}

const loadSelectedFoodData = function (context) {
    const foodIndex = context.data("id");
    const food = foodsInDiaryList[foodIndex];
    setCurrentFoodData(food);
    addFoodToPriceBreakdownChart(food);
}

const setCurrentFoodData = function (food) {
    currentFoodsData.setData(food);
};

const addFoodDataToCurrentData = function (food) {
    currentFoodsData.addData(food);
};

const convertFoodDataForDynamicWeight = function (food) {

    const newServingSize = food.dynamicProductWeight;

    Object.keys(food).forEach(property => {
        if (isFoodNutrient(property) && !isFoodCalories(property) && !isFoodPrice(property)) {
            food[property] = convertDataPerServing(food[property], newServingSize);
        }
    })

    food.calories = parseInt(convertDataPerServing(food.calories, newServingSize));
    food.price = convertDataPerServing(food.price, newServingSize, food.productWeight);
};

const isFoodNutrient = function (property) {
    return property !== 'name' && property !== 'dynamicProductWeight'
        && property !== 'productWeight' && property !== 'purchasePlace';
}

const isFoodCalories = function (property) {
    return property === 'calories';
}

const isFoodPrice = function (property) {
    return property === 'price';
}

const calculatePercentage = function (data, goalData) {
    return (data / goalData) * 100;
};

const setProgressBar = function ($bar, data, goalData) {

    let formattedData = data.toFixed(1);

    if (goalData === null) {
        $bar.width(`${0}%`);
        $bar.attr('aria-valuenow', 0);
        $bar.find('small').text('No target');
        $bar.parent().parent().parent().find('td:nth-child(2) span:first').text(formattedData);
        return;
    }

    let percentage = parseInt(calculatePercentage(data, goalData));
    $bar.width(`${percentage}%`);
    $bar.attr('aria-valuenow', percentage);
    let barTextTag = $bar.find('small');

    if (isMacronutrient($bar)) {
        barTextTag.text(`${formattedData} g / ${goalData} g (${percentage}%)`);
    } else {
        $bar.parent().parent().parent().find('td:nth-child(2) span:first').text(`${formattedData} / ${goalData}`);
        barTextTag.text(`${percentage}%`);
    }
};

const isMacronutrient = function ($bar) {
    return $bar.is($('#diary-data-calories'))
        || $bar.is($('#diary-data-protein'))
        || $bar.is($('#diary-data-carbs'))
        || $bar.is($('#diary-data-fat'));
}

const displayFoodsData = function () {
    Object.keys(currentFoodsData).forEach((value, index) => {
        setProgressBar($(diaryNutritionDataIds[index]), currentFoodsData[value], goalNutrients[value]);
    })

    console.trace();

    let caloriesBar = $('#diary-data-calories');
    let caloriesTextField = caloriesBar.find('small');
    caloriesTextField.text(caloriesTextField.text().replaceAll('g', 'kcal'));
};

const updateCaloriesBreakdownChart = function () {
    $('#calories-breakdown-calories-amount').text(currentFoodsData.calories);
    caloriesBreakdownChart.data.datasets[0].data[0] = currentFoodsData.protein;
    caloriesBreakdownChart.data.datasets[0].data[1] = currentFoodsData.carbohydrates;
    caloriesBreakdownChart.data.datasets[0].data[2] = currentFoodsData.fat;
    caloriesBreakdownChart.update();

};

const addFoodToPriceBreakdownChart = function (food) {
    $('#price-breakdown-price-amount').text(currentFoodsData.price.toFixed(2));
    priceBreakdownChart.data.datasets[0].data.push(food.price);
    priceBreakdownChart.data.labels.push(food.name);
    let red = Math.floor(Math.random() * 256);
    let green = Math.floor(Math.random() * 256);
    let blue = Math.floor(Math.random() * 256);
    priceBreakdownChart.data.datasets[0].backgroundColor.push(`rgba(${red}, ${green}, ${blue}, 0.8)`);
    priceBreakdownChart.data.datasets[0].borderColor.push(`rgba(${red}, ${green}, ${blue}, 1)`);
    priceBreakdownChart.options.tooltips.callbacks.title = formatTitle;
    priceBreakdownChart.update();
};
