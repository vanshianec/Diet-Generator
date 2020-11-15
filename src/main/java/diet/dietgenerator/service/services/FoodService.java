package diet.dietgenerator.service.services;

import diet.dietgenerator.data.models.base.BaseFood;
import diet.dietgenerator.service.models.food.BasicFoodServiceModel;
import diet.dietgenerator.service.models.food.CustomFoodDynamicWeightServiceModel;
import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import diet.dietgenerator.service.models.food.FoodRequiredNutrientsServiceModel;
import diet.dietgenerator.service.models.food.base.BaseFoodServiceModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {

    List<BaseFoodServiceModel> getAllFoods(Pageable pageable);

    List<BaseFoodServiceModel> getAllFoodsByFoodGroup(String foodGroup, Pageable pageable);

    void createFood(BaseFoodServiceModel serviceModel);

    List<BaseFoodServiceModel> getAllFoodsByMatchingName(String name);

    BaseFoodServiceModel getFoodById(Long id);

    //TODO

    // List<CustomFoodDynamicWeightServiceModel> generateLowestCostDiet(FoodRequiredNutrientsServiceModel requiredNutrients);
}
