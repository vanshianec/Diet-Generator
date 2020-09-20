package diet.dietgenerator.data.repositories.base;

import diet.dietgenerator.data.models.base.BaseFood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseFoodRepository <T extends BaseFood> extends JpaRepository<T, Long> {
    Page<T> findAll(Pageable pageable);

    Page<T> findAllByFoodGroup(String foodGroup, Pageable pageable);

    List<T> findAllByNameContainingIgnoreCase(String name);

    Optional<T> findById(Long id);
}
