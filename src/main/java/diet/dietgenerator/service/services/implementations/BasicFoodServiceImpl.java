package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.models.BasicFood;
import diet.dietgenerator.data.repositories.BasicFoodRepository;
import diet.dietgenerator.service.models.food.BasicFoodServiceModel;
import diet.dietgenerator.service.services.base.BaseFoodServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("BasicFood")
public class BasicFoodServiceImpl extends BaseFoodServiceImpl<BasicFood> {

    public BasicFoodServiceImpl(ModelMapper modelMapper, BasicFoodRepository repository) {
        super(modelMapper, repository, BasicFood.class, BasicFoodServiceModel.class);
    }
}
