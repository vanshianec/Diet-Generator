package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.repositories.FoodRepository;
import diet.dietgenerator.service.models.FoodServiceModel;
import diet.dietgenerator.service.services.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;

    public FoodServiceImpl(FoodRepository foodRepository, ModelMapper modelMapper) {
        this.foodRepository = foodRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FoodServiceModel> getAll(Pageable pageable) {
        return foodRepository.findAll(pageable)
                .stream()
                .map(f -> modelMapper.map(f, FoodServiceModel.class))
                .collect(Collectors.toList());
    }

    public List<FoodServiceModel> getAllByFoodGroup(String foodGroup, Pageable pageable) {
        return foodRepository.findAllByFoodGroup(foodGroup, pageable)
                .stream()
                .map(f -> modelMapper.map(f, FoodServiceModel.class))
                .collect(Collectors.toList());
    }
}