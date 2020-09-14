package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.repositories.BasicFoodRepository;
import diet.dietgenerator.data.repositories.CustomFoodRepository;
import diet.dietgenerator.service.models.food.BasicFoodServiceModel;
import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.service.services.base.BaseFoodServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl extends BaseFoodServiceImpl implements FoodService {

    private final BasicFoodRepository basicFoodRepository;
    private final CustomFoodRepository customFoodRepository;

    protected FoodServiceImpl(ModelMapper modelMapper, BasicFoodRepository basicFoodRepository, CustomFoodRepository customFoodRepository) {
        super(modelMapper);
        this.basicFoodRepository = basicFoodRepository;
        this.customFoodRepository = customFoodRepository;
    }

    public List<BasicFoodServiceModel> getAllBasicFoods(Pageable pageable) {
        return super.getAll(pageable, basicFoodRepository, BasicFoodServiceModel.class);
    }

    public List<CustomFoodServiceModel> getAllCustomFoods(Pageable pageable) {
        return super.getAll(pageable, customFoodRepository, CustomFoodServiceModel.class);
    }

    public List<BasicFoodServiceModel> getAllBasicFoodsByFoodGroup(String foodGroup, Pageable pageable) {
        return super.getAllByFoodGroup(foodGroup, pageable, basicFoodRepository, BasicFoodServiceModel.class);
    }

    public List<CustomFoodServiceModel> getAllCustomFoodsByFoodGroup(String foodGroup, Pageable pageable) {
        return super.getAllByFoodGroup(foodGroup, pageable, customFoodRepository, CustomFoodServiceModel.class);
    }
}
