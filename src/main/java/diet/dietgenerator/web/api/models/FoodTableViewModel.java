package diet.dietgenerator.web.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodTableViewModel {
    private String name;
    private String foodGroup;
    private Integer calories;
    private Float fat;
    private Float carbohydrates;
    private Float protein;
    private Float sugars;
    private Float additionalNutrient;

}
