package skillbox.com.users.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.dto.SubscriptionDto;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.entity.SubscriptionEntity;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class SubscriptionMapperTest {
    SubscriptionDto testSubscriptionDto;
    SubscriptionEntity testSubscriptionEntity;

    @BeforeEach
    void setUp() {
        testSubscriptionDto = new SubscriptionDto(1, Date.valueOf(LocalDate.now()), 2, 3, false);;
        testSubscriptionEntity = new SubscriptionEntity(1, Date.valueOf(LocalDate.now()), 2, 3, false);;
    }

    @Test
    void testDtoToEntity_Success() {
        // given
        SubscriptionEntity expectedSubscriptionEntity = new SubscriptionEntity(1, Date.valueOf(LocalDate.now()), 2, 3, false);
        // when
        SubscriptionEntity actualSubscriptionEntity = SubscriptionMapper.dtoToEntity(testSubscriptionDto);
        // then
        assertTrue(new ReflectionEquals(expectedSubscriptionEntity).matches(actualSubscriptionEntity));
    }

    @Test
    void testEntityToDto_Success() {
        // given
        SubscriptionDto expectedSubscriptionDto = new SubscriptionDto(1, Date.valueOf(LocalDate.now()), 2, 3, false);;
        // when
        SubscriptionDto actualSubscriptionDto = SubscriptionMapper.entityToDto(testSubscriptionEntity);
        // then
        assertTrue(new ReflectionEquals(expectedSubscriptionDto).matches(actualSubscriptionDto));
    }
}