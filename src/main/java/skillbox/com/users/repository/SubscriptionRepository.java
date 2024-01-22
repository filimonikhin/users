package skillbox.com.users.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import skillbox.com.users.entity.SubscriptionEntity;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
    //    @Query(value = "SELECT s FROM SubscriptionEntity s WHERE s.subscriberEntity.id = :p_subscriber_id AND s.subscribedEntity.id = :p_subscribed_id")
    //    Optional<SubscriptionEntity>  findBySubscriberIdAndSubscribedId (
    //            @Param("p_subscriber_id") Integer subscriberId,
    //            @Param("p_subscribed_id") Integer subscribedId
    //    );

    Optional<SubscriptionEntity> findBySubscriberIdAndSubscribedId(Integer subscriberId, Integer subscribedId);

    @Modifying
    @Transactional
    @Query (value = "UPDATE SubscriptionEntity se SET se.deleted = true WHERE se.id = :p_subscription_id")
    void deleteSubscriptionById(@Param("p_subscription_id") Integer subscriptionId);

    @Modifying
    @Transactional
    @Query (value = "UPDATE SubscriptionEntity se SET se.deleted = true WHERE se.subscriberId = :p_user_id OR se.subscribedId = :p_user_id")
    void deleteSubscriptionByUserId(@Param("p_user_id") Integer userId);

    List<SubscriptionEntity> findByDeletedFalse();
}
