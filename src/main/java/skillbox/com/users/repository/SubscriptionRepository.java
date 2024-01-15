package skillbox.com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import skillbox.com.users.entity.SubscriptionEntity;
import skillbox.com.users.entity.UserEntity;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
    @Query(value = "SELECT s FROM SubscriptionEntity s WHERE s.subscriberEntity.id = :p_subscriber_id AND s.subscribedEntity.id = :p_subscribed_id")
    Optional<SubscriptionEntity>  findBySubscriberIdAndSubscribedId (
            @Param("p_subscriber_id") Integer subscriberId,
            @Param("p_subscribed_id") Integer subscribedId
    );
}
