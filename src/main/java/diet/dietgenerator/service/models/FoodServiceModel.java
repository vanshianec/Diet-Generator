package diet.dietgenerator.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FoodServiceModel {

    private String name;
    private String foodGroup;
    private Integer calories;
    private Float fat;
    private Float carbohydrates;
    private Float protein;
}
