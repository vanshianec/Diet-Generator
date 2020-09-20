package diet.dietgenerator.service.services.base;

import diet.dietgenerator.data.models.base.BaseFood;
import diet.dietgenerator.data.repositories.base.BaseFoodRepository;
import diet.dietgenerator.service.models.food.base.BaseFoodServiceModel;
import diet.dietgenerator.service.services.BaseFoodService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseFoodServiceImpl implements BaseFoodService {

    private final ModelMapper modelMapper;

    protected BaseFoodServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T extends BaseFoodServiceModel, K extends BaseFood> List<T> getAll(Pageable pageable, BaseFoodRepository<K> repository, Class<T> typeParameterClass) {
        return repository.findAll(pageable)
                .stream()
                .map(f -> modelMapper.map(f, typeParameterClass))
                .collect(Collectors.toList());
    }

    public <T extends BaseFoodServiceModel, K extends BaseFood> List<T> getAllByFoodGroup(String foodGroup, Pageable pageable, BaseFoodRepository<K> repository, Class<T> typeParameterClass) {
        if (foodGroup == null) {
            return getAll(pageable, repository, typeParameterClass);
        }

        return repository.findAllByFoodGroup(foodGroup, pageable)
                .stream()
                .map(f -> modelMapper.map(f, typeParameterClass))
                .collect(Collectors.toList());
    }

    public <T extends BaseFoodServiceModel, K extends BaseFood> List<T> getAllFoodsByMatchingName(String name,  BaseFoodRepository<K> repository, Class<T> typeParameterClass) {
        return repository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(f -> modelMapper.map(f, typeParameterClass))
                .collect(Collectors.toList());
    }

    public <T extends BaseFoodServiceModel, K extends BaseFood> T getFoodById(Long id, BaseFoodRepository<K> repository, Class<T> typeParameterClass) {
        Optional<K> food = repository.findById(id);
        return modelMapper.map(food.get(), typeParameterClass);
    }

}
