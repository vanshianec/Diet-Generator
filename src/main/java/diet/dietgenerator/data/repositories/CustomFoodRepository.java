package diet.dietgenerator.data.repositories;

import diet.dietgenerator.data.models.CustomFood;
import diet.dietgenerator.data.repositories.base.BaseFoodRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFoodRepository extends BaseFoodRepository<CustomFood> {
}
