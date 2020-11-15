package diet.dietgenerator.service.services.base;

import diet.dietgenerator.data.models.base.BaseFood;
import diet.dietgenerator.data.repositories.base.BaseFoodRepository;
import diet.dietgenerator.exceptions.FoodNotFoundException;
import diet.dietgenerator.exceptions.RecourseNotFoundException;
import diet.dietgenerator.service.models.food.base.BaseFoodServiceModel;
import diet.dietgenerator.service.services.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public abstract class BaseFoodServiceImpl<T extends BaseFood> implements FoodService {

    private final ModelMapper modelMapper;
    private final BaseFoodRepository<T> repository;
    private final Class<T> foodClassType;
    private final Class<? extends BaseFoodServiceModel> foodServiceModelClassType;

    protected BaseFoodServiceImpl(ModelMapper modelMapper, BaseFoodRepository<T> repository, Class<T> foodClassType, Class<? extends BaseFoodServiceModel> foodServiceModelClassType) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.foodClassType = foodClassType;
        this.foodServiceModelClassType = foodServiceModelClassType;
    }

    //TODO SPLIT THESE METHODS

    @Override
    public List<BaseFoodServiceModel> getAllFoods(Pageable pageable) {
        return repository.findAll(pageable)
                .stream()
                .map(f -> modelMapper.map(f, foodServiceModelClassType))
                .collect(Collectors.toList());
    }

    @Override
    public List<BaseFoodServiceModel> getAllFoodsByFoodGroup(String foodGroup, Pageable pageable) {
        if (foodGroup == null) {
            return getAllFoods(pageable);
        }

        Page<T> foods = repository.findAllByFoodGroup(foodGroup, pageable);
        return foods
                .stream()
                .map(f -> modelMapper.map(f, foodServiceModelClassType))
                .collect(Collectors.toList());
    }

    @Override
    public List<BaseFoodServiceModel> getAllFoodsByMatchingName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(f -> modelMapper.map(f, foodServiceModelClassType))
                .collect(Collectors.toList());
    }

    @Override
    public BaseFoodServiceModel getFoodById(Long id) {
        Optional<T> food = repository.findById(id);
        if (!food.isPresent()) {
            throw new FoodNotFoundException("There is no such food with id: " + id);
        }

        return modelMapper.map(food.get(), foodServiceModelClassType);
    }

    @Override
    public void createFood(BaseFoodServiceModel serviceModel) {
        repository.save(modelMapper.map(serviceModel, foodClassType));
    }
}
