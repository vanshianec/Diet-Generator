package diet.dietgenerator.data.repositories;

import diet.dietgenerator.data.models.Food;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAll();
    List<Food> findAllByFoodGroup(String foodGroup, Pageable pageable);
}
