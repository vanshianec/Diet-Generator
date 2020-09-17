package diet.dietgenerator.data.repositories;

import diet.dietgenerator.data.models.BasicFood;
import diet.dietgenerator.data.repositories.base.BaseFoodRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicFoodRepository extends BaseFoodRepository<BasicFood> {

    List<BasicFood> findAllByNameContainingIgnoreCase(String name);
}
