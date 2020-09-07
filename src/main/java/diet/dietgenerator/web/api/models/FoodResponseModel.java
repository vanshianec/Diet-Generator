package diet.dietgenerator.web.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodResponseModel {

    private String name;
    private Integer calories;
    private Float fat;
    private Float carbohydrates;
    private Float protein;
    private Float fiber;
}
