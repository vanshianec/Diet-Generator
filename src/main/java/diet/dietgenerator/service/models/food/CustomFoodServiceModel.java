package diet.dietgenerator.service.models.food;

import diet.dietgenerator.service.models.food.base.BaseFoodServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CustomFoodServiceModel extends BaseFoodServiceModel {

    private String purchasePlace;
    private Integer productWeight;
    private Double price;
}
