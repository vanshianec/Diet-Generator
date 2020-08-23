package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.repositories.FoodRepository;
import diet.dietgenerator.service.models.FoodServiceModel;
import diet.dietgenerator.service.services.FoodService;
import org.modelmapper.ModelMapper;
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
    public List<FoodServiceModel> getAll() {
        return foodRepository.findAll()
                .stream()
                .map(f -> modelMapper.map(f, FoodServiceModel.class))
                .collect(Collectors.toList());
    }
}
