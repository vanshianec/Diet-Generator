package diet.dietgenerator.service.services;

import diet.dietgenerator.service.models.FoodServiceModel;

import java.util.List;

public interface FoodService {
    List<FoodServiceModel> getAll();
}
