package diet.dietgenerator.data.models;
import diet.dietgenerator.data.models.base.BaseFood;

import javax.persistence.*;

@Entity
@Table(name = "foods")
public class BasicFood extends BaseFood {

    @Override
    public FoodType getFoodType() {
        return FoodType.BASIC;
    }
}
