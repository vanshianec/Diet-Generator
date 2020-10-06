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

let totalCalories = 0;
let totalProtein = 0;
let totalCarbs = 0;
let totalFat = 0;
let totalFiber = 0;
let totalSugars = 0;
let totalAddedSugar = 0;
let totalSodium = 0;
let totalOmega3 = 0;
let totalOmega6 = 0;
let totalCholesterol = 0;
let totalSaturatedFats = 0;
let totalTransFats = 0;
let totalMonounsaturatedFats = 0;
let totalPolyunsaturatedFats = 0;
let totalVitaminB1 = 0;
let totalVitaminB2 = 0;
let totalVitaminB3 = 0;
let totalVitaminB5 = 0;
let totalVitaminB6 = 0;
let totalVitaminB9 = 0;
let totalVitaminB12 = 0;
let totalVitaminA = 0;
let totalVitaminC = 0;
let totalVitaminD = 0;
let totalVitaminE = 0;
let totalVitaminK = 0;
let totalCalcium = 0;
let totalCopper = 0;
let totalIron = 0;
let totalMagnesium = 0;
let totalManganese = 0;
let totalPhosphorus = 0;
let totalPotassium = 0;
let totalSelenium = 0;
let totalZinc = 0;
let totalPrice = 0;

let foodsInDiaryList = [];

const generateDiet = function () {

    resetCurrentFoodData();
    clearDiaryTableView();
    resetPriceBreakdownChart();
    goalCalories = $('#goal-calories-input').val();
    maxCalories = $('#max-calories-input').val();
    goalProtein = $('#goal-protein-input').val();
    goalCarbs = $('#goal-carbs-input').val();
    goalFat = $('#goal-fat-input').val();
    goalFiber = $('#goal-fiber-input').val();
    maxSaturatedFats = (goalCalories * 0.1) / 9;

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

const resetCurrentFoodData = function () {
    currentCalories = 0;
    currentProtein = 0;
    currentCarbs = 0;
    currentFat = 0;
    currentFiber = 0;
    currentSugars = 0;
    currentAddedSugar = 0;
    currentSodium = 0;
    currentOmega3 = 0;
    currentOmega6 = 0;
    currentCholesterol = 0;
    currentSaturatedFats = 0;
    currentTransFats = 0;
    currentMonounsaturatedFat = 0;
    currentPolyunsaturatedFat = 0;
    currentVitaminB1 = 0;
    currentVitaminB2 = 0;
    currentVitaminB3 = 0;
    currentVitaminB5 = 0;
    currentVitaminB6 = 0;
    currentVitaminB9 = 0;
    currentVitaminB12 = 0;
    currentVitaminA = 0;
    currentVitaminC = 0;
    currentVitaminD = 0;
    currentVitaminE = 0;
    currentVitaminK = 0;
    currentCalcium = 0;
    currentCopper = 0;
    currentIron = 0;
    currentMagnesium = 0;
    currentManganese = 0;
    currentPhosphorus = 0;
    currentPotassium = 0;
    currentSelenium = 0;
    currentZinc = 0;
    currentPrice = 0;
};

const clearDiaryTableView = function () {
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
    let convertedFood = convertFoodData(food, food.dynamicProductWeight);
    foodsInDiaryList.push(convertedFood);
    addFoodToDiaryTable(convertedFood);
    addFoodNutrientsToTotalNutrients(convertedFood);
    setFoodClickEvent();
    updateCaloriesBreakdownChart();
    updatePriceBreakdownChart(convertedFood);
    displayFoodData();
};

const addFoodToDiaryTable = function (food) {
    let row = `<tr data-id=${foodsInDiaryList.length - 1} class="clickable-food-diary">
                         <td>${food.name}, ${food.purchasePlace}</td>
                         <td>${food.dynamicProductWeight} g</td>
                         <td>${food.calories}</td>
                         <td>
                            <div class="row">
                                <span class="col-sm-7">${food.price.toFixed(2)} lv</span>
                                <div style="display: inline-block" class="remove-food-icon col-sm-5">
                                <i class="fa fa-trash fa-lg" data-toggle="tooltip" title="Remove"></i>
                                </div>  
                            </div>
                         </td>                              
                    </tr>`;
    setRemoveFoodClickEvent();
    $('#diary-foods-list').append(row);
};

const setRemoveFoodClickEvent = function () {
    $('.remove-food-icon').on('click', function () {
        const $this = $(this);
        const foodRow = $this.parent().parent().parent();
        const foodIndex = foodRow.data("id");
        console.log(foodIndex);
    });
}

const setFoodClickEvent = function () {
    const $clickable = $('.clickable-food-diary');

    $clickable.on('click', function () {
        const $this = $(this);
        $clickable.removeClass('table-active');
        $this.addClass('table-active');
        const foodIndex = $this.data("id");
        const food = foodsInDiaryList[foodIndex];
        saveFoodsData();
        displaySelectedFoodData(food);
        updateCaloriesBreakdownChart();
        resetPriceBreakdownChart();
        updatePriceBreakdownChart(food);
        displayFoodData();
    });
};

const saveFoodsData = function () {
    totalCalories = currentCalories;
    totalProtein = currentProtein;
    totalCarbs = currentCarbs;
    totalFat = currentFat;
    totalFiber = currentFiber;
    totalSugars = currentSugars;
    totalAddedSugar = currentAddedSugar;
    totalSodium = currentSodium;
    totalOmega3 = currentOmega3;
    totalOmega6 = currentOmega6;
    totalCholesterol = currentCholesterol;
    totalSaturatedFats = currentSaturatedFats;
    totalTransFats = currentTransFats;
    totalMonounsaturatedFats = currentMonounsaturatedFats;
    totalPolyunsaturatedFats = currentPolyunsaturatedFats;
    totalVitaminB1 = currentVitaminB1;
    totalVitaminB2 = currentVitaminB2;
    totalVitaminB3 = currentVitaminB3;
    totalVitaminB5 = currentVitaminB5;
    totalVitaminB6 = currentVitaminB6;
    totalVitaminB9 = currentVitaminB9;
    totalVitaminB12 = currentVitaminB12;
    totalVitaminA = currentVitaminA;
    totalVitaminC = currentVitaminC;
    totalVitaminD = currentVitaminD;
    totalVitaminE = currentVitaminE;
    totalVitaminK = currentVitaminK;
    totalCalcium = currentCalcium;
    totalCopper = currentCopper;
    totalIron = currentIron;
    totalMagnesium = currentMagnesium;
    totalManganese = currentManganese;
    totalPhosphorus = currentPhosphorus;
    totalPotassium = currentPotassium;
    totalSelenium = currentSelenium;
    totalZinc = currentZinc;
    totalPrice = currentPrice;
};

const displaySelectedFoodData = function (food) {
    currentCalories = food.calories;
    currentProtein = food.protein;
    currentCarbs = food.carbohydrates;
    currentFat = food.fat;
    currentFiber = food.fiber;
    currentSugars = food.sugars;
    currentAddedSugar = food.addedSugar;
    currentSodium = food.sodium;
    currentOmega3 = food.omega3;
    currentOmega6 = food.omega6;
    currentCholesterol = food.cholesterol;
    currentSaturatedFats = food.saturatedFats;
    currentTransFats = food.transFats;
    currentMonounsaturatedFats = food.monounsaturatedFats;
    currentPolyunsaturatedFats = food.polyunsaturatedFats;
    currentVitaminB1 = food.vitaminB1;
    currentVitaminB2 = food.vitaminB2;
    currentVitaminB3 = food.vitaminB3;
    currentVitaminB5 = food.vitaminB5;
    currentVitaminB6 = food.vitaminB6;
    currentVitaminB9 = food.vitaminB9;
    currentVitaminB12 = food.vitaminB12;
    currentVitaminA = food.vitaminA;
    currentVitaminC = food.vitaminC;
    currentVitaminD = food.vitaminD;
    currentVitaminE = food.vitaminE;
    currentVitaminK = food.vitaminK;
    currentCalcium = food.calcium;
    currentCopper = food.copper;
    currentIron = food.iron;
    currentMagnesium = food.magnesium;
    currentManganese = food.manganese;
    currentPhosphorus = food.phosphorus;
    currentPotassium = food.potassium;
    currentSelenium = food.selenium;
    currentZinc = food.zinc;
    currentPrice = food.price;
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

const displayFoodData = function () {
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
    let red = Math.floor(Math.random() * 256);
    let green = Math.floor(Math.random() * 256);
    let blue = Math.floor(Math.random() * 256);
    priceBreakdownChart.data.datasets[0].backgroundColor.push(`rgba(${red}, ${green}, ${blue}, 0.8)`);
    priceBreakdownChart.data.datasets[0].borderColor.push(`rgba(${red}, ${green}, ${blue}, 1)`);
    priceBreakdownChart.options.tooltips.callbacks.title = formatTitle;
    priceBreakdownChart.update();
};

$('#add-food-to-diary-button').on('click', addFoodToDiary);

$('#generate-diet-button').on('click', generateDiet);
