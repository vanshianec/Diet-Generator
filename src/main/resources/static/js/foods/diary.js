const goalCalories = 2000;
const goalProtein = 200;
const goalCarbs = 200;
const goalFat = 45;
const goalFiber = 38;
const goalSodium = 1500;
const goalOmega3 = 1600;
const goalOmega6 = 17000;
const goalB1 = 1.2;
const goalB2 = 1.3;
const goalB3 = 16;
const goalB5 = 5;
const goalB6 = 1.3;
const goalB9 = 400;
const goalB12 = 2.4;
const goalA = 3000;
const goalC = 90;
const goalD = 600;
const goalE = 15;
const goalK = 120;
const goalCalcium = 1000;
const goalCopper = 0.9;
const goalIron = 8;
const goalMagnesium = 400;
const goalManganese = 2.3;
const goalPhosphorus = 700;
const goalPotassium = 3400;
const goalSelenium = 55;
const goalZinc = 11;

let foodsInDiaryList = [];

const addFoodToDiary = function () {
    let servingSize = $('#serving-size').val();
    fetch(URLS.getCustomFood + '?foodId=' + foodId + '&servingSize=' + servingSize)
        .then(handleResponse)
        .then(displayFoodInDiary)
        .catch(handleError);
};

const displayFoodInDiary = function (food) {
    foodsInDiaryList.push(food);
    let convertedFood = convertFoodData(food, food.dynamicProductWeight);
    addFoodToDiaryTable(convertedFood);
    displayFoodData(convertedFood);
    //displayMicronutrientsFoodData(food);
};

const addFoodToDiaryTable = function (food) {
    let row = `<tr>
                    <td>${food.name}, ${food.purchasePlace}</td>
                         <td>${food.dynamicProductWeight} g</td>
                         <td>${food.calories}</td>
                         <td>${food.price} lv</td>
                    </tr>`;
    $('#diary-foods-list').append(row);
};

const convertFoodData = function (food, newServingSize) {

    let foodCopy = Object.assign({}, food);

    foodCopy.calories = parseInt(convertDataPerServing(food.calories, newServingSize));
    foodCopy.fat = convertDataPerServing(food.fat, newServingSize);
    foodCopy.protein = convertDataPerServing(food.protein, newServingSize);
    foodCopy.carbohydrates = convertDataPerServing(food.carbohydrates, newServingSize);
    foodCopy.sugars = convertDataPerServing(food.sugars, newServingSize);
    foodCopy.fiber = convertDataPerServing(food.fiber, newServingSize);
    foodCopy.cholesterol = convertDataPerServing(food.cholesterol, newServingSize);
    foodCopy.saturatedFats = convertDataPerServing(food.saturatedFats, newServingSize);
    foodCopy.calcium = convertDataPerServing(food.calcium, newServingSize);
    foodCopy.iron = convertDataPerServing(food.iron, newServingSize);
    foodCopy.potassium = convertDataPerServing(food.potassium, newServingSize);
    foodCopy.magnesium = convertDataPerServing(food.magnesium, newServingSize);
    foodCopy.vitaminA = convertDataPerServing(food.vitaminA, newServingSize);
    foodCopy.vitaminC = convertDataPerServing(food.vitaminC, newServingSize);
    foodCopy.vitaminB12 = convertDataPerServing(food.vitaminB12, newServingSize);
    foodCopy.vitaminE = convertDataPerServing(food.vitaminE, newServingSize);
    foodCopy.addedSugar = convertDataPerServing(food.addedSugar, newServingSize);
    foodCopy.omega3 = convertDataPerServing(food.omega3, newServingSize);
    foodCopy.omega6 = convertDataPerServing(food.omega6, newServingSize);
    foodCopy.transFats = convertDataPerServing(food.transFats, newServingSize);
    foodCopy.phosphorus = convertDataPerServing(food.phosphorus, newServingSize);
    foodCopy.sodium = convertDataPerServing(food.sodium, newServingSize);
    foodCopy.zinc = convertDataPerServing(food.zinc, newServingSize);
    foodCopy.copper = convertDataPerServing(food.copper, newServingSize);
    foodCopy.manganese = convertDataPerServing(food.manganese, newServingSize);
    foodCopy.selenium = convertDataPerServing(food.selenium, newServingSize);
    foodCopy.vitaminB1 = convertDataPerServing(food.vitaminB1, newServingSize);
    foodCopy.vitaminB2 = convertDataPerServing(food.vitaminB2, newServingSize);
    foodCopy.vitaminB3 = convertDataPerServing(food.vitaminB3, newServingSize);
    foodCopy.vitaminB5 = convertDataPerServing(food.vitaminB5, newServingSize);
    foodCopy.vitaminB6 = convertDataPerServing(food.vitaminB6, newServingSize);
    foodCopy.vitaminB9 = convertDataPerServing(food.vitaminB9, newServingSize);
    foodCopy.vitaminD = convertDataPerServing(food.vitaminD, newServingSize);
    foodCopy.vitaminK = convertDataPerServing(food.vitaminK, newServingSize);
    foodCopy.monounsaturatedFats = convertDataPerServing(food.monounsaturatedFats, newServingSize);
    foodCopy.polyunsaturatedFats = convertDataPerServing(food.polyunsaturatedFats, newServingSize);
    foodCopy.price = convertDataPerServing(food.price, newServingSize, food.productWeight);

    return foodCopy;
};

const calculatePercentage = function (data, goalData) {
    return (data / goalData) * 100;
};

const setProgressBar = function ($bar, data, goalData, macros) {

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

    if (macros) {
        barTextTag.text(`${formattedData} g / ${goalData} g (${percentage}%)`);
    } else {
        $bar.parent().parent().parent().find('td:nth-child(2) span:first').text(`${formattedData} / ${goalData}`);
        barTextTag.text(`${percentage}%`);
    }
};

function displayFoodData(food) {
    let caloriesBar = $('#diary-data-calories');
    setProgressBar(caloriesBar, food.calories, goalCalories, true);
    let caloriesTextField = caloriesBar.find('small');
    caloriesTextField.text(caloriesTextField.text().replaceAll('g', 'kcal'));
    setProgressBar($('#diary-data-protein'), food.protein, goalProtein, true);
    setProgressBar($('#diary-data-fat'), food.fat, goalFat, true);
    setProgressBar($('#diary-data-carbs'), food.carbohydrates, goalCarbs, true);
    setProgressBar($('#diary-data-fiber'), food.fiber, goalFiber);
    setProgressBar($('#diary-data-sugars'), food.sugars, null);
    setProgressBar($('#diary-data-added-sugar'), food.addedSugar, null);
    setProgressBar($('#diary-data-sodium'), food.sodium, goalSodium);
    setProgressBar($('#diary-data-cholesterol'), food.cholesterol, null);
    setProgressBar($('#diary-data-fat-saturated'), food.saturatedFats, null);
    setProgressBar($('#diary-data-fat-trans-fats'), food.transFats, null);
    setProgressBar($('#diary-data-fat-monounsaturated'), food.monounsaturatedFats, null);
    setProgressBar($('#diary-data-fat-polyunsaturated'), food.polyunsaturatedFats, null);
    setProgressBar($('#diary-data-fat-omega3'), food.omega3, goalOmega3);
    setProgressBar($('#diary-data-fat-omega6'), food.omega6, goalOmega6);
    setProgressBar($('#diary-data-vitamin-b1'), food.vitaminB1, goalB1);
    setProgressBar($('#diary-data-vitamin-b2'), food.vitaminB2, goalB2);
    setProgressBar($('#diary-data-vitamin-b3'), food.vitaminB3, goalB3);
    setProgressBar($('#diary-data-vitamin-b5'), food.vitaminB5, goalB5);
    setProgressBar($('#diary-data-vitamin-b6'), food.vitaminB6, goalB6);
    setProgressBar($('#diary-data-vitamin-b9'), food.vitaminB9, goalB9);
    setProgressBar($('#diary-data-vitamin-b12'), food.vitaminB12, goalB12);
    setProgressBar($('#diary-data-vitamin-a'), food.vitaminA, goalA);
    setProgressBar($('#diary-data-vitamin-c'), food.vitaminC, goalC);
    setProgressBar($('#diary-data-vitamin-d'), food.vitaminD, goalD);
    setProgressBar($('#diary-data-vitamin-e'), food.vitaminE, goalE);
    setProgressBar($('#diary-data-vitamin-k'), food.vitaminK, goalK);
    setProgressBar($('#diary-data-calcium'), food.calcium, goalCalcium);
    setProgressBar($('#diary-data-copper'), food.copper, goalCopper);
    setProgressBar($('#diary-data-iron'), food.iron, goalIron);
    setProgressBar($('#diary-data-magnesium'), food.magnesium, goalMagnesium);
    setProgressBar($('#diary-data-manganese'), food.manganese, goalManganese);
    setProgressBar($('#diary-data-phosphorus'), food.phosphorus, goalPhosphorus);
    setProgressBar($('#diary-data-potassium'), food.potassium, goalPotassium);
    setProgressBar($('#diary-data-selenium'), food.selenium, goalSelenium);
    setProgressBar($('#diary-data-zinc'), food.zinc, goalZinc);

    $('#calories-breakdown-calories-amount').text(food.calories);
    $('#price-breakdown-price-amount').text(food.price.toFixed(2));
    caloriesBreakdownChart.data.datasets[0].data[0] = food.protein;
    caloriesBreakdownChart.data.datasets[0].data[1] = food.carbohydrates;
    caloriesBreakdownChart.data.datasets[0].data[2] = food.fat;
    caloriesBreakdownChart.update();

    priceBreakdownChart.data.datasets[0].data.push(food.price);
    priceBreakdownChart.data.labels.push(food.name);
    priceBreakdownChart.data.datasets[0].backgroundColor.push('rgba(92, 184, 92, 0.8)');
    priceBreakdownChart.data.datasets[0].borderColor.push('rgba(92, 184, 92, 1)');
    priceBreakdownChart.options.tooltips.callbacks.title = formatTitle;
    priceBreakdownChart.update();

}


$('#add-food-to-diary-button').on('click', addFoodToDiary);
