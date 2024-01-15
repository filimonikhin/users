package skillbox.com.users.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import skillbox.com.users.entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByLogin(String login);

    /*
        @Query(value ="SELECT u FROM UserEntity u WHERE u.login = :p_login")
        Optional<UserEntity> findByLogin(@Param("p_login") String login);
    */

    @Query (value = "SELECT u FROM UserEntity u WHERE u.cityEntity.id = :p_city_id")
    List<UserEntity> findUsersByCityId(@Param("p_city_id") Integer cityId);

}
