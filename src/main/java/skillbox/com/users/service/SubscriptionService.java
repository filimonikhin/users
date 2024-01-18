package skillbox.com.users.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import skillbox.com.users.entity.SubscriptionEntity;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public List<SubscriptionEntity> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public String createSubscription(SubscriptionEntity subscriptionEntity) {
        SubscriptionEntity savedSubscription = subscriptionRepository.save(subscriptionEntity);
        Optional<UserEntity> subscriber = userRepository.findById(subscriptionEntity.getSubscriberId());
        Optional<UserEntity> subscribed = userRepository.findById(subscriptionEntity.getSubscribedId());
        return String.format("Создана подписка пользователя %s на пользователя %s", subscriber.get().getName(), subscribed.get().getName());
    }

    public String deleteSubscription(Integer subscriptionId) {
        if (!subscriptionRepository.existsById(subscriptionId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // soft удаление - для подписки проставляется маркер удаления
        subscriptionRepository.deleteSubscriptionById(subscriptionId);
        return String.format("Подписка с id = %s успешно удалена", subscriptionId);
    }
}
