package skillbox.com.users.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.repository.CityRepository;
import java.util.List;
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

    public List<CityEntity> getAllCities() {
        return cityRepository.findAllByOrderByIdAsc();
    }

    public CityEntity getCity(Integer cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String createCity(CityEntity cityEntity) {
        CityEntity savedCity = cityRepository.save(cityEntity);
        return String.format("Город %s добавлен в базу id = %s", savedCity.getName(), savedCity.getId());
    }

    public String deleteCity(Integer cityId) {
        if (!cityRepository.existsById(cityId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        cityRepository.deleteById(cityId);
        return String.format("Город с id = %s успешно удален", cityId);
    }

    // если нужно передавать все поля в JSON
    public String updateCity(CityEntity cityEntity, Integer cityId) {

       if (!cityRepository.existsById(cityId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (cityEntity.getId() == null) cityEntity.setId(cityId);

        CityEntity savedcity = cityRepository.save(cityEntity);

        return String.format("Город %s успешно обновлен", savedcity.getName());
    }

    // если в запросе JSON передается тоько изменяемое поле
    /*
    public String updateCity(Map<String, Object> fields, Integer cityId) {
        Optional<CityEntity> existingCity = cityRepository.findById(cityId);

        if (existingCity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        CityEntity cityEntity = existingCity.get();

        fields.forEach ((fieldName, fieldValue) -> {
            // поис поля в классе
            // System.out.println ("field name: " + fieldName + "\n field value: " + fieldValue);
            Field field = ReflectionUtils.findField (cityEntity.getClass(), fieldName);

            if (field != null) {
                // делаем доступным для изменения private поля
                field.setAccessible (true);

                // установка значения fieldValue для поля field объекта userEntity
                ReflectionUtils.setField (field, cityEntity, fieldValue);

                cityRepository.save (cityEntity);
            }
        });

        return String.format("Город %s успешно обновлен", cityEntity.getName());
    }
    */
}
