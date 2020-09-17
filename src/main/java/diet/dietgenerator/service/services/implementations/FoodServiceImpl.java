package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.models.CustomFood;
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
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl extends BaseFoodServiceImpl implements FoodService {

    private final BasicFoodRepository basicFoodRepository;
    private final CustomFoodRepository customFoodRepository;
    private final ModelMapper modelMapper;

    protected FoodServiceImpl(ModelMapper modelMapper, BasicFoodRepository basicFoodRepository, CustomFoodRepository customFoodRepository, ModelMapper modelMapper1) {
        super(modelMapper);
        this.basicFoodRepository = basicFoodRepository;
        this.customFoodRepository = customFoodRepository;
        this.modelMapper = modelMapper1;
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
        return basicFoodRepository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(f -> modelMapper.map(f, BasicFoodServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BasicFoodServiceModel getBasicFoodByName(String foodName) {
        return modelMapper.map(basicFoodRepository.findByName(foodName), BasicFoodServiceModel.class);
    }
}
