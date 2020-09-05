package diet.dietgenerator.data.models;

import diet.dietgenerator.data.models.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "foods")
@Getter
@Setter
public class Food extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "food_group")
    private String foodGroup;

    @Column(nullable = false)
    private Integer calories;

    /* measured in grams */
    @Column(nullable = false)
    private Float fat;

    /* measured in grams */
    @Column(nullable = false)
    private Float carbohydrates;

    /* measured in grams */
    @Column(nullable = false)
    private Float protein;

    /* measured in grams */
    @Column
    private Float sugars;

    /* measured in grams */
    @Column
    private Float fiber;

    /* measured in mg */
    @Column
    private Float cholesterol;

    /* measured in grams */
    @Column(name = "saturated_fats")
    private Float saturatedFats;

    /* measured in mg */
    @Column
    private Float calcium;

    /* measured in mg */
    @Column
    private Float iron;

    /* measured in mg */
    @Column
    private Float potassium;

    /* measured in mg */
    @Column
    private Float magnesium;

    /* measured in IU */
    @Column(name = "vitamin_a")
    private Float vitaminA;

    /* measured in mg */
    @Column(name = "vitamin_c")
    private Float vitaminC;

    /* measured in mcg */
    @Column(name = "vitamin_b12")
    private Float vitaminB12;

    /* measured in mg */
    @Column(name = "vitamin_e")
    private Float vitaminE;

    @Column(name = "added_sugar")
    private Float addedSugar;

    /* measured in mg */
    @Column
    private Float omega3;

    /* measured in mg */
    @Column
    private Float omega6;

    /* measured in grams */
    @Column(name = "trans_fats")
    private Float transFats;

    /* measured in mg */
    @Column
    private Float phosphorus;

    /* measured in mg */
    @Column
    private Float sodium;

    /* measured in mg */
    @Column
    private Float zinc;

    /* measured in mg */
    @Column
    private Float copper;

    /* measured in mg */
    @Column
    private Float manganese;

    /* measured in mcg */
    @Column
    private Float selenium;

    /* measured in mg */
    @Column(name = "vitamin_b1")
    private Float vitaminB1;

    /* measured in mg */
    @Column(name = "vitamin_b2")
    private Float vitaminB2;

    /* measured in mg */
    @Column(name = "vitamin_b3")
    private Float vitaminB3;

    /* measured in mg */
    @Column(name = "vitamin_b5")
    private Float vitaminB5;

    /* measured in mg */
    @Column(name = "vitamin_b6")
    private Float vitaminB6;

    /* measured in mcg */
    @Column(name = "vitamin_b9")
    private Float vitaminB9;

    /* measured in IU */
    @Column(name = "vitamin_d")
    private Float vitaminD;

    /* measured in mcg */
    @Column(name = "vitamin_k")
    private Float vitaminK;

    /* measured in mg */
    @Column(name = "monounsaturated_fats")
    private Float monounsaturatedFats;

    /* measured in mg */
    @Column(name = "polyunsaturated_fats")
    private Float polyunsaturatedFats;
}
