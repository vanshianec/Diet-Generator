package diet.dietgenerator.service.services;

import diet.dietgenerator.service.models.food.BasicFoodServiceModel;
import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {

    List<BasicFoodServiceModel> getAllBasicFoods(Pageable pageable);

    List<CustomFoodServiceModel> getAllCustomFoods(Pageable pageable);

    List<BasicFoodServiceModel> getAllBasicFoodsByFoodGroup(String foodGroup, Pageable pageable);

    List<CustomFoodServiceModel> getAllCustomFoodsByFoodGroup(String foodGroup, Pageable pageable);
}
