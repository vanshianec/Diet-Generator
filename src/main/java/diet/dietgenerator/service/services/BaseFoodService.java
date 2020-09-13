package diet.dietgenerator.service.services;


import diet.dietgenerator.data.models.base.BaseFood;
import diet.dietgenerator.data.repositories.base.BaseFoodRepository;
import diet.dietgenerator.service.models.food.base.BaseFoodServiceModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseFoodService {

    <T extends BaseFoodServiceModel, K extends BaseFood> List<T> getAll(Pageable pageable, BaseFoodRepository<K> repository, Class<T> typeParameterClass);

    <T extends BaseFoodServiceModel, K extends BaseFood> List<T> getAllByFoodGroup(String foodGroup, Pageable pageable, BaseFoodRepository<K> repository, Class<T> typeParameterClass);
}
