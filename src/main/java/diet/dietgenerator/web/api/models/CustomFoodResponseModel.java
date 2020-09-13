package diet.dietgenerator.web.api.models;

import diet.dietgenerator.web.api.models.base.BaseFoodResponseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomFoodResponseModel extends BaseFoodResponseModel {

    private String purchasePlace;
    private Integer productWeight;
    private Double price;
}
