package diet.dietgenerator.data.models;

import diet.dietgenerator.data.models.base.BaseFood;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "custom_foods")
@Getter
@Setter
public class CustomFood extends BaseFood {

    @Column(name = "purchase_place")
    private String purchasePlace;

    @Column(name = "serving_size")
    private Integer servingSize;

    @Column
    private Double price;

    @Column(nullable = false)
    private Boolean verified;

}
