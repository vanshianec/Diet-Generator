package diet.dietgenerator.data.repositories;

import diet.dietgenerator.data.models.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Page<Food> findAll(Pageable pageable);

    Page<Food> findAllByFoodGroup(String foodGroup, Pageable pageable);
}
