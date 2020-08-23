package diet.dietgenerator.web.view.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FoodViewModel {

    private String name;
    private String foodGroup;
    private int calories;
    private float fat;
    private float carbohydrates;
    private float protein;
}
