package diet.dietgenerator.service.services;

import diet.dietgenerator.service.models.FoodServiceModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {
    List<FoodServiceModel> getAll(Pageable pageable);

    List<FoodServiceModel> getAllByFoodGroup(String foodGroup, Pageable pageable);
}
