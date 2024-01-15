package skillbox.com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skillbox.com.users.entity.CityEntity;
import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
   Optional<CityEntity> findById(Integer cityId);
   List<CityEntity> findAllByName(String name);
   List<CityEntity> findAllByNameLike(String name);
}
