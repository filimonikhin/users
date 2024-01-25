package skillbox.com.users.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.entity.CityEntity;
import static org.junit.jupiter.api.Assertions.*;

class CityMapperTest {
    CityDto testCityDto;
    CityEntity testCityEntity;

    @BeforeEach
    void setUp() {
        testCityDto = new CityDto(1, "Test City");
        testCityEntity = new CityEntity(1, "Test City");
    }

    @Test
    void testDtoToEntity_Success() {
        // given
        CityEntity expectedCityEntity = new CityEntity(1, "Test City");
        // when
        CityEntity actualCityEntity = CityMapper.dtoToEntity(testCityDto);
        // then
        assertTrue(new ReflectionEquals(expectedCityEntity).matches(actualCityEntity));
    }

    @Test
    void testDtoToEntity_IfDtoIsNull_Success() {
        // given
        CityDto cityDto = null;
        // when
        CityEntity actualCityEntity = CityMapper.dtoToEntity(cityDto);
        // then
        assertNull(actualCityEntity);
    }

    @Test
    void testEntityToDto_Success() {
        // given
        CityDto expectedCityDto = new CityDto(1, "Test City");
        // when
        CityDto actualCityDto = CityMapper.entityToDto(testCityEntity);
        // then
        assertTrue(new ReflectionEquals(expectedCityDto).matches(actualCityDto));
    }

    @Test
    void testEntityToDto_IfEntityIsNull_Success() {
        // given
        CityEntity cityEntity = null;
        // when
        CityDto actualCityDto = CityMapper.entityToDto(cityEntity);
        // then
        assertNull(actualCityDto);
    }

}