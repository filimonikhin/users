package skillbox.com.users.service;

import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.mapper.CityMapper;
import skillbox.com.users.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitiesServiceTest {
    @Mock
    CityRepository cityRepository;
    // CityRepository cityRepository = Mockito.mock(CityRepository.class);
    @InjectMocks
    CitiesService citiesService;
    // public CitiesService citiesService = new CitiesService(cityRepository);
    CityEntity testCityEntity;
    CityDto testCityDto;

    @BeforeEach
    void setUp() {
        testCityDto = new CityDto(1, "Ленинград");
        testCityEntity = CityMapper.dtoToEntity(testCityDto);
    }

    @Test
    @DisplayName("Get all cities success")
    void tesGetAllCities_Success() {
        // given
        List<CityEntity> testCities = new ArrayList<>();
        testCities.add(new CityEntity());
        when(cityRepository.findAllByOrderByIdAsc()).thenReturn(testCities);
        // when
        List<CityDto> citiesDto = citiesService.getAllCities();
        // then
        assertEquals(testCities.size(), citiesDto.size());
        verify(cityRepository, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    void testGetCity_Success() {
        // given
        Integer cityId = 1;
        when(cityRepository.findById(cityId)).thenReturn(Optional.of(testCityEntity));
        // when
        CityDto actualCityDto = citiesService.getCity(cityId);
        CityDto expectedCityDto = new CityDto(1, "Ленинград");

        // then
        assertTrue(new ReflectionEquals(expectedCityDto).matches(actualCityDto));
        verify(cityRepository, times(1)).findById(cityId);
    }

    @Test
    void testGetCity_Fail() {
        // given
        Integer cityId = 1;
        when(cityRepository.findById(cityId)).thenThrow(PersistenceException.class);
        // when
        Executable executable = () -> citiesService.getCity(cityId);
        // then
        assertThrows(PersistenceException.class, executable);
    }

    @Test
    @DisplayName("Test create city success")
    void testCreateCity_Success() {
        // given
        when(cityRepository.save(any(CityEntity.class))).thenReturn(testCityEntity);
        // when
        CityDto actualCityDto = citiesService.createCity(testCityDto);
        // then
        CityDto expectedCityDto = new CityDto(1, "Ленинград");
        assertTrue(new ReflectionEquals(expectedCityDto).matches(actualCityDto));
        verify(cityRepository, times(1)).save(any(CityEntity.class));
    }

    @Test
    @DisplayName("Test create city fail")
    void testCreateCity_Fail() {
        // given
        when(cityRepository.save(any(CityEntity.class))).thenThrow(PersistenceException.class);
        // when
        Executable executable = () -> citiesService.createCity(testCityDto);
        // then
        assertThrows(PersistenceException.class, executable);
        verify(cityRepository, times(1)).save(any(CityEntity.class));
    }

    @Test
    @DisplayName("Test delete when city found")
    void testDeleteCity_whenCityFound_Success() {
        // given
        Integer cityId = 1;
        when (cityRepository.existsById(cityId)).thenReturn(Boolean.TRUE);
        // when
        boolean idDeleted =  citiesService.deleteCity(cityId);
        // then
        verify(cityRepository, times(1)).deleteById(cityId);
    }

    @Test
    @DisplayName("Test delete when city not found")
    void testDeleteCity_whenCityNotFound_Success() {
        // given
        Integer cityId = 1;
        when (cityRepository.existsById(cityId)).thenReturn(Boolean.FALSE);
        // when
        boolean isDeleted =  citiesService.deleteCity(cityId);
        // then
        verify(cityRepository, never()).deleteById(cityId); // метод не вызывается
    }

    @Test
    void testUpdateCity_Success() {
        // given
        Integer cityId = 1;
        CityDto modifyCityDto = testCityDto.clone();
        modifyCityDto.setName("Л е н и н г р а д");
        when(cityRepository.existsById(cityId)).thenReturn(Boolean.TRUE);
        when(cityRepository.save(any(CityEntity.class))).thenReturn(CityMapper.dtoToEntity(modifyCityDto));
        // when
        boolean isUpdated = citiesService.updateCity(modifyCityDto, cityId);
        // then
        assertTrue(isUpdated);
        verify(cityRepository, times(1)).save(any(CityEntity.class));
    }
}