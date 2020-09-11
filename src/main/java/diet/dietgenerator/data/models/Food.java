package diet.dietgenerator.data.models;
import diet.dietgenerator.data.models.base.BaseFood;

import javax.persistence.*;

@Entity
@Table(name = "foods")
public class Food extends BaseFood {
}
