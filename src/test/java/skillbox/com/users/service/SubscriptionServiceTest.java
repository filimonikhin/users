package skillbox.com.users.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import skillbox.com.users.dto.SubscriptionDto;
import skillbox.com.users.entity.SubscriptionEntity;
import skillbox.com.users.mapper.SubscriptionMapper;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {
    @Mock
    SubscriptionRepository subscriptionRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    SubscriptionService subscriptionService;

    SubscriptionEntity testSubscriptionEntity;
    SubscriptionDto testSubscriptionDto;

    @BeforeEach
    void setUp() {
        testSubscriptionDto = new SubscriptionDto(1, Date.valueOf(LocalDate.now()), 2, 3, false);
        testSubscriptionEntity = SubscriptionMapper.dtoToEntity(testSubscriptionDto);
    }

    @Test
    void testGetAllSubscriptions_Success() {
        // given
        List<SubscriptionEntity> subscriptionEntities = List.of(testSubscriptionEntity);
        when(subscriptionRepository.findByDeletedFalse()).thenReturn(subscriptionEntities);
        // when
        List<SubscriptionDto> actualSubscriptionsDto = subscriptionService.getAllSubscriptions();
        // then
        assertEquals(1, actualSubscriptionsDto.size());
        verify(subscriptionRepository, times(1)).findByDeletedFalse();
    }

    @Test
    void testCreateSubscription_Success() {
        // given
        when(subscriptionRepository.save(any(SubscriptionEntity.class)))
                .thenReturn(testSubscriptionEntity);
        // when
        SubscriptionDto actualSubscriptionsDto = subscriptionService.createSubscription(testSubscriptionDto);
        // then
        assertTrue(new ReflectionEquals(testSubscriptionDto).matches(actualSubscriptionsDto));
        verify(subscriptionRepository, times(1)).save(any(SubscriptionEntity.class));
    }

    @Test
    void testDeleteSubscription_whenSubscriptionFound_Success() {
        // given
        Integer idSubscription = 1;
        when(subscriptionRepository.existsById(idSubscription)).thenReturn(Boolean.TRUE);
        // when
        boolean isDeleted = subscriptionService.deleteSubscription(idSubscription);
        // then
        verify(subscriptionRepository, times(1)).deleteSubscriptionById(idSubscription);
    }

    @Test
    void testDeleteSubscription_whenSubscriptionNotFound_Success() {
        // given
        Integer idSubscription = 1;
        when(subscriptionRepository.existsById(idSubscription)).thenReturn(Boolean.FALSE);
        // when
        boolean isDeleted = subscriptionService.deleteSubscription(idSubscription);
        // then
        verify(subscriptionRepository, never()).deleteSubscriptionById(idSubscription);
    }
}