package skillbox.com.users.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.mapper.CityMapper;
import skillbox.com.users.repository.CityRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
// import org.springframework.util.ReflectionUtils;
// import java.lang.reflect.Field;
// import java.util.Map;
// import java.util.Optional;

@Service
public class CitiesService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CitiesService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public List<CityDto> getAllCities() {
        return cityRepository.findAllByOrderByIdAsc().stream()
                .map(cityMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public CityDto getCity(Integer cityId) {
        return cityRepository.findById(cityId)
                .map(cityMapper::entityToDto)
                .orElse(null);
    }

    public CityDto createCity(CityDto cityDto) {
        CityEntity cityEntity = cityMapper.dtoToEntity(cityDto);
        CityEntity savedCity = cityRepository.save(cityEntity);
        return cityMapper.entityToDto(savedCity);
    }

    public boolean deleteCity(Integer cityId) {
        if (!cityRepository.existsById(cityId)){
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return false;
        }
        cityRepository.deleteById(cityId);
        return true;
    }

    // если нужно передавать все поля в JSON
    public boolean updateCity(CityDto cityDto, Integer cityId) {

       if (!cityRepository.existsById(cityId) || cityDto == null) {
           return false;
           // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (cityDto.getId() == null) {
            cityDto.setId(cityId);
        }

        CityEntity savedCity = cityRepository.save(cityMapper.dtoToEntity(cityDto));

        return true;
        //return String.format("Город %s успешно обновлен", savedcity.getName());
    }

}
