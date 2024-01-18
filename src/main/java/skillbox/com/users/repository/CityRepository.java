package skillbox.com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skillbox.com.users.entity.CityEntity;
import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
   List<CityEntity> findAllByOrderByIdAsc();
   List<CityEntity> findAllByName(String name);
   List<CityEntity> findAllByNameLike(String name);
}
