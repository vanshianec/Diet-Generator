class FoodData {
    calories;
    protein;
    carbohydrates;
    fat;
    fiber;
    sugars;
    addedSugar;
    sodium;
    omega3;
    omega6;
    cholesterol;
    saturatedFats;
    transFats;
    monounsaturatedFats;
    polyunsaturatedFats;
    vitaminB1;
    vitaminB2;
    vitaminB3;
    vitaminB5;
    vitaminB6;
    vitaminB9;
    vitaminB12;
    vitaminA;
    vitaminC;
    vitaminD;
    vitaminE;
    vitaminK;
    calcium;
    copper;
    iron;
    magnesium;
    manganese;
    phosphorus;
    potassium;
    selenium;
    zinc;
    price;

    constructor(otherFoodData) {
        if (otherFoodData !== undefined) {
            this.setData(otherFoodData);
            return;
        }

        //set default value for each field as 0;
        Object.keys(this).forEach(property => {
            this[property] = 0;
        })
    }

    setData(otherFoodData) {
        Object.assign(this, otherFoodData);
    }

    addData(otherFoodData) {
        Object.keys(this).forEach(property => {
            this[property] += otherFoodData[property];
        })
    }
}