package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.models.CustomFood;
import diet.dietgenerator.data.models.base.BaseFood;
import diet.dietgenerator.data.repositories.BasicFoodRepository;
import diet.dietgenerator.data.repositories.CustomFoodRepository;
import diet.dietgenerator.exceptions.FoodNotFoundException;
import diet.dietgenerator.service.models.food.BasicFoodServiceModel;
import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import diet.dietgenerator.service.models.food.base.BaseFoodServiceModel;
import diet.dietgenerator.service.services.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    private final Class<BasicFoodServiceModel> basicFoodClassType = BasicFoodServiceModel.class;
    private final Class<CustomFoodServiceModel> customFoodClassType = CustomFoodServiceModel.class;

    private final ModelMapper modelMapper;
    private final BasicFoodRepository basicFoodRepository;
    private final CustomFoodRepository customFoodRepository;

    public FoodServiceImpl(ModelMapper modelMapper, BasicFoodRepository basicFoodRepository, CustomFoodRepository customFoodRepository) {
        this.modelMapper = modelMapper;
        this.basicFoodRepository = basicFoodRepository;
        this.customFoodRepository = customFoodRepository;
    }

    @Override
    public List<BasicFoodServiceModel> getAllBasicFoods(Pageable pageable) {
        return mapToServiceModels(basicFoodRepository.findAll(pageable), basicFoodClassType);
    }

    @Override
    public List<BasicFoodServiceModel> getAllBasicFoodsByFoodGroup(String foodGroup, Pageable pageable) {
        if(foodGroup == null){
            return getAllBasicFoods(pageable);
        }

        return mapToServiceModels(basicFoodRepository.findAllByFoodGroup(foodGroup, pageable), basicFoodClassType);
    }

    @Override
    public List<BasicFoodServiceModel> getAllBasicFoodsByMatchingName(String name) {
        return mapToServiceModels(basicFoodRepository.findAllByNameContainingIgnoreCase(name), basicFoodClassType);
    }

    @Override
    public BasicFoodServiceModel getBasicFoodById(Long id) {
        return mapToServiceModel(basicFoodRepository.findById(id), basicFoodClassType);
    }

    @Override
    public List<CustomFoodServiceModel> getAllCustomFoods(Pageable pageable) {
        return mapToServiceModels(customFoodRepository.findAll(pageable), customFoodClassType);
    }

    @Override
    public List<CustomFoodServiceModel> getAllCustomFoodsByFoodGroup(String foodGroup, Pageable pageable) {
        if(foodGroup == null){
            return getAllCustomFoods(pageable);
        }

        return mapToServiceModels(customFoodRepository.findAllByFoodGroup(foodGroup, pageable), customFoodClassType);
    }

    @Override
    public List<CustomFoodServiceModel> getAllCustomFoodsByMatchingName(String name) {
        return mapToServiceModels(customFoodRepository.findAllByNameContainingIgnoreCase(name), customFoodClassType);
    }

    @Override
    public CustomFoodServiceModel getCustomFoodById(Long id) {
        return mapToServiceModel(customFoodRepository.findById(id), customFoodClassType);
    }

    @Override
    public void createCustomFood(CustomFoodServiceModel serviceModel) {
        customFoodRepository.save(modelMapper.map(serviceModel, CustomFood.class));
    }

    private <T extends BaseFoodServiceModel> List<T> mapToServiceModels(List<? extends BaseFood> entities, Class<T> classType) {
        return entities.stream()
                .map(f -> modelMapper.map(f, classType))
                .collect(Collectors.toList());
    }

    private <T extends BaseFoodServiceModel> List<T> mapToServiceModels(Page<? extends BaseFood> entities, Class<T> classType) {
        return entities.stream()
                .map(f -> modelMapper.map(f, classType))
                .collect(Collectors.toList());
    }

    private <T extends BaseFoodServiceModel> T mapToServiceModel(Optional<? extends BaseFood> entity, Class<T> classType) {
        if (!entity.isPresent()) {
            throw new FoodNotFoundException("There is no such entity in the database");
        }

        return modelMapper.map(entity.get(), classType);
    }

}
