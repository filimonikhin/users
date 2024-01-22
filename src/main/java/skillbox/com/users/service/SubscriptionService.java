package skillbox.com.users.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import skillbox.com.users.dto.SubscriptionDto;
import skillbox.com.users.entity.SubscriptionEntity;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.mapper.SubscriptionMapper;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    public List<SubscriptionDto> getAllSubscriptions() {
        //return subscriptionRepository.findAll();
        return subscriptionRepository.findByDeletedFalse().stream()
                .map(subscriptionMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) {
        SubscriptionEntity subscriptionEntity = subscriptionMapper.dtoToEntity(subscriptionDto);
        SubscriptionEntity savedSubscription = subscriptionRepository.save(subscriptionEntity);
        return subscriptionMapper.entityToDto(savedSubscription);
        // Optional<UserEntity> subscriber = userRepository.findById(subscriptionEntity.getSubscriberId());
        // Optional<UserEntity> subscribed = userRepository.findById(subscriptionEntity.getSubscribedId());
        // return String.format("Создана подписка пользователя %s на пользователя %s", subscriber.get().getName(), subscribed.get().getName());
    }

    public boolean deleteSubscription(Integer subscriptionId) {
        if (!subscriptionRepository.existsById(subscriptionId)){
            return false;
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // soft удаление - для подписки проставляется маркер удаления
        subscriptionRepository.deleteSubscriptionById(subscriptionId);
        return true;
        // return String.format("Подписка с id = %s успешно удалена", subscriptionId);
    }
}
