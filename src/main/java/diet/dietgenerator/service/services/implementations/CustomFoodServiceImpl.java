package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.models.CustomFood;
import diet.dietgenerator.data.repositories.CustomFoodRepository;
import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import diet.dietgenerator.service.services.base.BaseFoodServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("CustomFood")
public class CustomFoodServiceImpl extends BaseFoodServiceImpl<CustomFood> {

    public CustomFoodServiceImpl(ModelMapper modelMapper, CustomFoodRepository repository) {
        super(modelMapper, repository, CustomFood.class, CustomFoodServiceModel.class);
    }
}
