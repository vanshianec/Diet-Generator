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
    //TODO create new object
    console.log(foodsInDiaryList[0]);
    convertFoodData(food, food.dynamicProductWeight);
    console.log(food);
    addFoodToDiaryTable(food);
    //displayGeneralFoodData(food);
    //displayMicronutrientsFoodData(food);
};

function addFoodToDiaryTable(food){
    let row = `<tr>
                    <td>${food.name}, ${food.purchasePlace}</td>
                         <td>${food.dynamicProductWeight} g</td>
                         <td>${food.calories}</td>
                         <td>${food.price} lv</td>
                    </tr>`;
    $('#diary-foods-list').append(row);
}

function convertFoodData(food, newServingSize) {

    food.calories = parseInt(convertDataPerServing(food.calories, newServingSize));
    food.fat = convertDataPerServing(food.fat, newServingSize);
    food.protein = convertDataPerServing(food.protein, newServingSize);
    food.carbohydrates = convertDataPerServing(food.carbohydrates, newServingSize);
    food.sugars = convertDataPerServing(food.sugars, newServingSize);
    food.fiber = convertDataPerServing(food.fiber, newServingSize);
    food.cholesterol = convertDataPerServing(food.cholesterol, newServingSize);
    food.saturatedFats = convertDataPerServing(food.saturatedFats, newServingSize);
    food.calcium = convertDataPerServing(food.calcium, newServingSize);
    food.iron = convertDataPerServing(food.iron, newServingSize);
    food.potassium = convertDataPerServing(food.potassium, newServingSize);
    food.magnesium = convertDataPerServing(food.magnesium, newServingSize);
    food.vitaminA = convertDataPerServing(food.vitaminA, newServingSize);
    food.vitaminC = convertDataPerServing(food.vitaminC, newServingSize);
    food.vitaminB12 = convertDataPerServing(food.vitaminB12, newServingSize);
    food.vitaminE = convertDataPerServing(food.vitaminE, newServingSize);
    food.addedSugar = convertDataPerServing(food.addedSugar, newServingSize);
    food.omega3 = convertDataPerServing(food.omega3, newServingSize);
    food.omega6 = convertDataPerServing(food.omega6, newServingSize);
    food.transFats = convertDataPerServing(food.transFats, newServingSize);
    food.phosphorus = convertDataPerServing(food.phosphorus, newServingSize);
    food.sodium = convertDataPerServing(food.sodium, newServingSize);
    food.zinc = convertDataPerServing(food.zinc, newServingSize);
    food.copper = convertDataPerServing(food.copper, newServingSize);
    food.manganese = convertDataPerServing(food.manganese, newServingSize);
    food.selenium = convertDataPerServing(food.selenium, newServingSize);
    food.vitaminB1 = convertDataPerServing(food.vitaminB1, newServingSize);
    food.vitaminB2 = convertDataPerServing(food.vitaminB2, newServingSize);
    food.vitaminB3 = convertDataPerServing(food.vitaminB3, newServingSize);
    food.vitaminB5 = convertDataPerServing(food.vitaminB5, newServingSize);
    food.vitaminB6 = convertDataPerServing(food.vitaminB6, newServingSize);
    food.vitaminB9 = convertDataPerServing(food.vitaminB9, newServingSize);
    food.vitaminD = convertDataPerServing(food.vitaminD, newServingSize);
    food.vitaminK = convertDataPerServing(food.vitaminK, newServingSize);
    food.monounsaturatedFats = convertDataPerServing(food.monounsaturatedFats, newServingSize);
    food.polyunsaturatedFats = convertDataPerServing(food.polyunsaturatedFats, newServingSize);
    food.price = convertDataPerServing(food.price, newServingSize, food.productWeight).toFixed(2);
}

function displayGeneralFoodData(food){

}

$('#add-food-to-diary-button').on('click', addFoodToDiary);
