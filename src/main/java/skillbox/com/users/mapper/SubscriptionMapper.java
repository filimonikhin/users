package skillbox.com.users.mapper;

import org.springframework.stereotype.Component;
import skillbox.com.users.dto.SubscriptionDto;
import skillbox.com.users.entity.SubscriptionEntity;

@Component
public class SubscriptionMapper {
    public static SubscriptionEntity dtoToEntity (SubscriptionDto subscriptionDto) {
        if (subscriptionDto == null) {
            return null;
        }
        return new SubscriptionEntity(
                subscriptionDto.getId(),
                subscriptionDto.getSubscribeDate(),
                subscriptionDto.getSubscriberId(),
                subscriptionDto.getSubscribedId(),
                subscriptionDto.isDeleted()
        );
    }

    public static SubscriptionDto entityToDto(SubscriptionEntity subscriptionEntity) {
        if (subscriptionEntity == null) {
            return  null;
        }
        return new SubscriptionDto(
                subscriptionEntity.getId(),
                subscriptionEntity.getSubscribeDate(),
                subscriptionEntity.getSubscriberId(),
                subscriptionEntity.getSubscribedId(),
                subscriptionEntity.isDeleted()
        );
    }
}
