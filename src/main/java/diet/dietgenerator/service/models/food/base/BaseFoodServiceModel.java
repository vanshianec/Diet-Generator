package diet.dietgenerator.service.models.food.base;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class BaseFoodServiceModel {

    private Long id;
    private String name;
    private String foodGroup;
    private Integer calories;
    private Float fat;
    private Float carbohydrates;
    private Float protein;
    private Float sugars;
    private Float fiber;
    private Float cholesterol;
    private Float saturatedFats;
    private Float calcium;
    private Float iron;
    private Float potassium;
    private Float magnesium;
    private Float vitaminA;
    private Float vitaminC;
    private Float vitaminB12;
    private Float vitaminE;
    private Float addedSugar;
    private Float omega3;
    private Float omega6;
    private Float transFats;
    private Float phosphorus;
    private Float sodium;
    private Float zinc;
    private Float copper;
    private Float manganese;
    private Float selenium;
    private Float vitaminB1;
    private Float vitaminB2;
    private Float vitaminB3;
    private Float vitaminB5;
    private Float vitaminB6;
    private Float vitaminB9;
    private Float vitaminD;
    private Float vitaminK;
    private Float monounsaturatedFats;
    private Float polyunsaturatedFats;

}
