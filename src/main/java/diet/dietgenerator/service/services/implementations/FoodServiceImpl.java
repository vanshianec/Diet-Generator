package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.models.CustomFood;
import diet.dietgenerator.data.repositories.BasicFoodRepository;
import diet.dietgenerator.data.repositories.CustomFoodRepository;
import diet.dietgenerator.service.models.food.BasicFoodServiceModel;
import diet.dietgenerator.service.models.food.CustomFoodDynamicWeightServiceModel;
import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import diet.dietgenerator.service.models.food.FoodRequiredNutrientsServiceModel;
import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.service.services.base.BaseFoodServiceImpl;
import diet.dietgenerator.utils.linearsolver.FoodsLowestCostSolver;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl extends BaseFoodServiceImpl implements FoodService {

    private final BasicFoodRepository basicFoodRepository;
    private final CustomFoodRepository customFoodRepository;
    private final ModelMapper modelMapper;

    protected FoodServiceImpl(ModelMapper modelMapper, BasicFoodRepository basicFoodRepository, CustomFoodRepository customFoodRepository) {
        super(modelMapper);
        this.basicFoodRepository = basicFoodRepository;
        this.customFoodRepository = customFoodRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BasicFoodServiceModel> getAllBasicFoods(Pageable pageable) {
        return super.getAll(pageable, basicFoodRepository, BasicFoodServiceModel.class);
    }

    @Override
    public List<CustomFoodServiceModel> getAllCustomFoods(Pageable pageable) {
        return super.getAll(pageable, customFoodRepository, CustomFoodServiceModel.class);
    }

    @Override
    public List<BasicFoodServiceModel> getAllBasicFoodsByFoodGroup(String foodGroup, Pageable pageable) {
        return super.getAllByFoodGroup(foodGroup, pageable, basicFoodRepository, BasicFoodServiceModel.class);
    }

    @Override
    public List<CustomFoodServiceModel> getAllCustomFoodsByFoodGroup(String foodGroup, Pageable pageable) {
        return super.getAllByFoodGroup(foodGroup, pageable, customFoodRepository, CustomFoodServiceModel.class);
    }

    @Override
    public void createCustomFood(CustomFoodServiceModel serviceModel) {
        customFoodRepository.save(modelMapper.map(serviceModel, CustomFood.class));
    }

    @Override
    public List<BasicFoodServiceModel> getAllBasicFoodsByMatchingName(String name) {
        return super.getAllFoodsByMatchingName(name, basicFoodRepository, BasicFoodServiceModel.class);
    }

    @Override
    public List<CustomFoodServiceModel> getAllCustomFoodsByMatchingName(String name) {
        return super.getAllFoodsByMatchingName(name, customFoodRepository, CustomFoodServiceModel.class);
    }

    @Override
    public BasicFoodServiceModel getBasicFoodById(Long id) {
        return super.getFoodById(id, basicFoodRepository, BasicFoodServiceModel.class);
    }

    @Override
    public CustomFoodServiceModel getCustomFoodById(Long id) {
        return super.getFoodById(id, customFoodRepository, CustomFoodServiceModel.class);
    }

    @Override
    public List<CustomFoodDynamicWeightServiceModel> generateLowestCostDiet(FoodRequiredNutrientsServiceModel requiredNutrients) {
        FoodsLowestCostSolver solver = new FoodsLowestCostSolver(modelMapper, requiredNutrients, getAllCustomFoods(Pageable.unpaged()));
        return solver.getLowestCostFoods();
    }
}
