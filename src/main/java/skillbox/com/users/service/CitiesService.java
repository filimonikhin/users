package skillbox.com.users.service;

import org.springframework.stereotype.Service;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.mapper.CityMapper;
import skillbox.com.users.repository.CityRepository;
import java.util.List;
import java.util.stream.Collectors;
// import org.springframework.util.ReflectionUtils;
// import java.lang.reflect.Field;
// import java.util.Map;
// import java.util.Optional;

@Service
public class CitiesService {
    private final CityRepository cityRepository;

    public CitiesService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityDto> getAllCities() {
        return cityRepository.findAllByOrderByIdAsc().stream()
                .map(CityMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public CityDto getCity(Integer cityId) {
        return cityRepository.findById(cityId)
                .map(CityMapper::entityToDto)
                .orElse(null);
    }

    public CityDto createCity(CityDto cityDto) {
        CityEntity cityEntity = CityMapper.dtoToEntity(cityDto);
        CityEntity savedCity = cityRepository.save(cityEntity);
        return CityMapper.entityToDto(savedCity);
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

        CityEntity savedCity = cityRepository.save(CityMapper.dtoToEntity(cityDto));

        return true;
        //return String.format("Город %s успешно обновлен", savedcity.getName());
    }

}
