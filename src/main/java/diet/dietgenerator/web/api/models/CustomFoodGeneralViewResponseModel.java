package diet.dietgenerator.web.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomFoodGeneralViewResponseModel {

    private Integer calories;
    private Double fat;
    private Double carbohydrates;
    private Double protein;
    private Double price;
    private Integer productWeight;

}
