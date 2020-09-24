//TODO rename 'current' to 'total' :DD

let goalCalories = 2000;
let goalProtein = 200;
let goalCarbs = 200;
let goalFat = 45;
let goalFiber = 38;
const goalSugars = null;
const goalAddedSugar = null;
const goalSodium = null;
const goalOmega3 = null;
const goalOmega6 = null;
const goalCholesterol = null;
const goalSaturatedFats = null;
const goalTransFats = null;
const goalMonounsaturatedFats = null;
const goalPolyunsaturatedFats = null;

const goalVitaminB1 = 1.2;
const goalVitaminB2 = 1.3;
const goalVitaminB3 = 16;
const goalVitaminB5 = 5;
const goalVitaminB6 = 1.3;
const goalVitaminB9 = 400;
const goalVitaminB12 = 2.4;
const goalVitaminA = 3000;
const goalVitaminC = 90;
const goalVitaminE = 15;
const goalVitaminK = 120;
const goalVitaminD = null;
const goalCalcium = 1000;
const goalCopper = 0.9;
const goalIron = 8;
const goalMagnesium = 400;
const goalManganese = 2.3;
const goalPhosphorus = 700;
const goalPotassium = 4700;
const goalSelenium = 55;
const goalZinc = 11;

let maxCalories = 2100;
let maxSaturatedFats = null;
const maxCholesterol = 300;
const maxVitaminA = 10000;
const maxVitaminB3 = 35;
const maxVitaminB6 = 100;
const maxVitaminC = 2000;
const maxVitaminD = 2000;
const maxVitaminE = 1000;
const maxVitaminB9 = 1000;
const maxVitaminK = 2000;
const maxCalcium = 2500;
const maxCopper = 10;
const maxIron = 45;
const maxManganese = 11;
const maxPhosphorus = 4000;
const maxSelenium = 400;
const maxZinc = 40;

/*
const maxCholesterol = null;
const maxVitaminA = null;
const maxVitaminB3 = null;
const maxVitaminB6 = null;
const maxVitaminC = null;
const maxVitaminD = null;
const maxVitaminE = null;
const maxVitaminB9 = null;
const maxVitaminK = null;
const maxCalcium = null;
const maxCopper = null;
const maxIron = null;
const maxManganese = null;
const maxPhosphorus = null;
const maxSelenium = null;
const maxZinc = null;

 */

let currentCalories = 0;
let currentProtein = 0;
let currentCarbs = 0;
let currentFat = 0;
let currentFiber = 0;
let currentSugars = 0;
let currentAddedSugar = 0;
let currentSodium = 0;
let currentOmega3 = 0;
let currentOmega6 = 0;
let currentCholesterol = 0;
let currentSaturatedFats = 0;
let currentTransFats = 0;
let currentMonounsaturatedFats = 0;
let currentPolyunsaturatedFats = 0;
let currentVitaminB1 = 0;
let currentVitaminB2 = 0;
let currentVitaminB3 = 0;
let currentVitaminB5 = 0;
let currentVitaminB6 = 0;
let currentVitaminB9 = 0;
let currentVitaminB12 = 0;
let currentVitaminA = 0;
let currentVitaminC = 0;
let currentVitaminD = 0;
let currentVitaminE = 0;
let currentVitaminK = 0;
let currentCalcium = 0;
let currentCopper = 0;
let currentIron = 0;
let currentMagnesium = 0;
let currentManganese = 0;
let currentPhosphorus = 0;
let currentPotassium = 0;
let currentSelenium = 0;
let currentZinc = 0;
let currentPrice = 0;

//TODO use in the future when removing or updating foods in the diary
let foodsInDiaryList = [];

const generateDiet = function () {

    goalCalories = $('#goal-calories-input').val();
    maxCalories = $('#max-calories-input').val();
    goalProtein = $('#goal-protein-input').val();
    goalCarbs = $('#goal-carbs-input').val();
    goalFat = $('#goal-fat-input').val();
    goalFiber = $('#goal-fiber-input').val();
    //maxSaturatedFats = (goalCalories * 0.1) / 9;

    //TODO split into two objects

    let requiredNutrients = {
        maxCalories,
        maxCholesterol,
        maxVitaminA,
        maxVitaminB3,
        maxVitaminB6,
        maxVitaminC,
        maxVitaminD,
        maxVitaminE,
        maxVitaminB9,
        maxVitaminK,
        maxCalcium,
        maxCopper,
        maxIron,
        maxManganese,
        maxPhosphorus,
        maxSelenium,
        maxZinc,
        goalCalories,
        goalProtein,
        goalCarbs,
        goalFat,
        goalFiber,
        goalSugars,
        goalAddedSugar,
        goalSodium,
        goalOmega3,
        goalOmega6,
        goalCholesterol,
        goalSaturatedFats,
        goalTransFats,
        goalMonounsaturatedFats,
        goalPolyunsaturatedFats,
        goalVitaminD,
        goalVitaminB1,
        goalVitaminB2,
        goalVitaminB3,
        goalVitaminB5,
        goalVitaminB6,
        goalVitaminB9,
        goalVitaminB12,
        goalVitaminA,
        goalVitaminC,
        goalVitaminE,
        goalVitaminK,
        goalCalcium,
        goalCopper,
        goalIron,
        goalMagnesium,
        goalManganese,
        goalPhosphorus,
        goalPotassium,
        goalSelenium,
        goalZinc
    };

    fetch(URLS.generateDiet, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requiredNutrients)
    })
        .then(handleResponse)
        .then(displayGeneratedFoodsInDiary)
        .catch(handleError);

};

const displayGeneratedFoodsInDiary = function (foods) {
    foods.forEach(food => displayFoodInDiary(food));
};

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
    addFoodNutrientsToTotalNutrients(convertedFood);
    updateCaloriesBreakdownChart();
    updatePriceBreakdownChart(convertedFood);
    displayFoodData();
};

const addFoodToDiaryTable = function (food) {
    let row = `<tr>
                    <td>${food.name}, ${food.purchasePlace}</td>
                         <td>${food.dynamicProductWeight} g</td>
                         <td>${food.calories}</td>
                         <td>${food.price.toFixed(2)} lv</td>
                    </tr>`;
    $('#diary-foods-list').append(row);
};

const addFoodNutrientsToTotalNutrients = function (food) {
    currentCalories += food.calories;
    currentProtein += food.protein;
    currentCarbs += food.carbohydrates;
    currentFat += food.fat;
    currentFiber += food.fiber;
    currentSugars += food.sugars;
    currentAddedSugar += food.addedSugar;
    currentSodium += food.sodium;
    currentOmega3 += food.omega3;
    currentOmega6 += food.omega6;
    currentCholesterol += food.cholesterol;
    currentSaturatedFats += food.saturatedFats;
    currentTransFats += food.transFats;
    currentMonounsaturatedFats += food.monounsaturatedFats;
    currentPolyunsaturatedFats += food.polyunsaturatedFats;
    currentVitaminB1 += food.vitaminB1;
    currentVitaminB2 += food.vitaminB2;
    currentVitaminB3 += food.vitaminB3;
    currentVitaminB5 += food.vitaminB5;
    currentVitaminB6 += food.vitaminB6;
    currentVitaminB9 += food.vitaminB9;
    currentVitaminB12 += food.vitaminB12;
    currentVitaminA += food.vitaminA;
    currentVitaminC += food.vitaminC;
    currentVitaminD += food.vitaminD;
    currentVitaminE += food.vitaminE;
    currentVitaminK += food.vitaminK;
    currentCalcium += food.calcium;
    currentCopper += food.copper;
    currentIron += food.iron;
    currentMagnesium += food.magnesium;
    currentManganese += food.manganese;
    currentPhosphorus += food.phosphorus;
    currentPotassium += food.potassium;
    currentSelenium += food.selenium;
    currentZinc += food.zinc;
    currentPrice += food.price;
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

let displayFoodData = function () {
    let caloriesBar = $('#diary-data-calories');
    setProgressBar(caloriesBar, currentCalories, goalCalories, true);
    let caloriesTextField = caloriesBar.find('small');
    caloriesTextField.text(caloriesTextField.text().replaceAll('g', 'kcal'));
    setProgressBar($('#diary-data-protein'), currentProtein, goalProtein, true);
    setProgressBar($('#diary-data-fat'), currentFat, goalFat, true);
    setProgressBar($('#diary-data-carbs'), currentCarbs, goalCarbs, true);
    setProgressBar($('#diary-data-fiber'), currentFiber, goalFiber);
    setProgressBar($('#diary-data-sugars'), currentSugars, goalSugars);
    setProgressBar($('#diary-data-added-sugar'), currentAddedSugar, goalAddedSugar);
    setProgressBar($('#diary-data-sodium'), currentSodium, goalSodium);
    setProgressBar($('#diary-data-cholesterol'), currentCholesterol, goalCholesterol);
    setProgressBar($('#diary-data-fat-saturated'), currentSaturatedFats, goalSaturatedFats);
    setProgressBar($('#diary-data-fat-trans-fats'), currentTransFats, goalTransFats);
    setProgressBar($('#diary-data-fat-monounsaturated'), currentMonounsaturatedFats, goalMonounsaturatedFats);
    setProgressBar($('#diary-data-fat-polyunsaturated'), currentPolyunsaturatedFats, goalPolyunsaturatedFats);
    setProgressBar($('#diary-data-fat-omega3'), currentOmega3, goalOmega3);
    setProgressBar($('#diary-data-fat-omega6'), currentOmega6, goalOmega6);
    setProgressBar($('#diary-data-vitamin-b1'), currentVitaminB1, goalVitaminB1);
    setProgressBar($('#diary-data-vitamin-b2'), currentVitaminB2, goalVitaminB2);
    setProgressBar($('#diary-data-vitamin-b3'), currentVitaminB3, goalVitaminB3);
    setProgressBar($('#diary-data-vitamin-b5'), currentVitaminB5, goalVitaminB5);
    setProgressBar($('#diary-data-vitamin-b6'), currentVitaminB6, goalVitaminB6);
    setProgressBar($('#diary-data-vitamin-b9'), currentVitaminB9, goalVitaminB9);
    setProgressBar($('#diary-data-vitamin-b12'), currentVitaminB12, goalVitaminB12);
    setProgressBar($('#diary-data-vitamin-a'), currentVitaminA, goalVitaminA);
    setProgressBar($('#diary-data-vitamin-c'), currentVitaminC, goalVitaminC);
    setProgressBar($('#diary-data-vitamin-d'), currentVitaminD, goalVitaminD);
    setProgressBar($('#diary-data-vitamin-e'), currentVitaminE, goalVitaminE);
    setProgressBar($('#diary-data-vitamin-k'), currentVitaminK, goalVitaminK);
    setProgressBar($('#diary-data-calcium'), currentCalcium, goalCalcium);
    setProgressBar($('#diary-data-copper'), currentCopper, goalCopper);
    setProgressBar($('#diary-data-iron'), currentIron, goalIron);
    setProgressBar($('#diary-data-magnesium'), currentMagnesium, goalMagnesium);
    setProgressBar($('#diary-data-manganese'), currentManganese, goalManganese);
    setProgressBar($('#diary-data-phosphorus'), currentPhosphorus, goalPhosphorus);
    setProgressBar($('#diary-data-potassium'), currentPotassium, goalPotassium);
    setProgressBar($('#diary-data-selenium'), currentSelenium, goalSelenium);
    setProgressBar($('#diary-data-zinc'), currentZinc, goalZinc);
};

const updateCaloriesBreakdownChart = function () {
    $('#calories-breakdown-calories-amount').text(currentCalories);
    caloriesBreakdownChart.data.datasets[0].data[0] = currentProtein;
    caloriesBreakdownChart.data.datasets[0].data[1] = currentCarbs;
    caloriesBreakdownChart.data.datasets[0].data[2] = currentFat;
    caloriesBreakdownChart.update();

};

const updatePriceBreakdownChart = function (food) {
    $('#price-breakdown-price-amount').text(currentPrice.toFixed(2));
    priceBreakdownChart.data.datasets[0].data.push(food.price);
    priceBreakdownChart.data.labels.push(food.name);
    priceBreakdownChart.data.datasets[0].backgroundColor.push('rgba(92, 184, 92, 0.8)');
    priceBreakdownChart.data.datasets[0].borderColor.push('rgba(92, 184, 92, 1)');
    priceBreakdownChart.options.tooltips.callbacks.title = formatTitle;
    priceBreakdownChart.update();
};

$('#add-food-to-diary-button').on('click', addFoodToDiary);

$('#generate-diet-button').on('click', generateDiet);
