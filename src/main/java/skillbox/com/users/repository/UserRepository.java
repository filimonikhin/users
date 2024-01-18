package skillbox.com.users.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import skillbox.com.users.entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByLogin(String login);
    Boolean existsByLogin(String userLogin);

    /*
    @Query(value ="SELECT u FROM UserEntity u WHERE u.login = :p_login")
    Optional<UserEntity> findByLogin(@Param("p_login") String login);
    */

    /*
    @Query (value = "SELECT u FROM UserEntity u WHERE u.cityEntity.id = :p_city_id")
    List<UserEntity> findUsersByCityId(@Param("p_city_id") Integer cityId);
    */

    @Modifying
    @Transactional
    @Query (value = "UPDATE UserEntity u SET u.deleted = true WHERE u.id = :p_user_id")
    void deleteUserById(@Param("p_user_id") Integer userId);

    @Modifying
    @Transactional
    @Query (value = "UPDATE UserEntity u SET u.deleted = true WHERE u.login = :p_user_login")
    void deleteByUserLogin(@Param("p_user_login") String userLogin);

    List<UserEntity> findAllByOrderByIdAsc();

    /*
    @Query (value = "SELECT u FROM UserEntity u WHERE u.login = :p_user_login")
    Boolean existByLogin(@Param("p_user_login") String userLogin);
    */

}
