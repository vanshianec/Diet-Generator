package diet.dietgenerator.data.models;

import diet.dietgenerator.data.models.base.BaseFood;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "custom_foods")
@Getter
@Setter
public class CustomFood extends BaseFood {

    @Column(name = "purchase_place")
    private String purchasePlace;

    @Column(name = "product_weight")
    private Integer productWeight;

    /*TODO add serving size */

    @Column
    private Double price;

    @Column(nullable = false)
    private Boolean verified;

    @ManyToMany
    @JoinTable(
            name = "users_custom_foods",
            joinColumns = {@JoinColumn(name = "custom_food_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;
}
