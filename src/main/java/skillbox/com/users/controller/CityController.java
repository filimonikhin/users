package skillbox.com.users.controller;

import org.springframework.web.bind.annotation.*;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.service.CitiesService;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    private final CitiesService citiesService;

    public CityController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @PostMapping
    String CreateCity(@RequestBody CityEntity cityEntity) {
        return citiesService.createCity(cityEntity);
    }

    @DeleteMapping("/{cityId}")
    String deleteCity(@PathVariable Integer cityId) {
        return citiesService.deleteCity(cityId);
    }

    // если в запросе JSON передается тоько изменяемое поле
    /*
    @PatchMapping("/{cityId}")
    String UpdateCity(@RequestBody Map<String, Object> fields, @PathVariable Integer cityId) {
        return citiesService.updateCity(fields, cityId);
    }
    */

    /* если нужно передавать все поля Entity в JSON (но не сработает, если в JSON передаем только изменяемое поле
       т.к. в этом случает в Entity другие поля будут = null)
    */
    @PatchMapping("/{cityId}")
    String UpdateCity(@RequestBody CityEntity cityEntity, @PathVariable Integer cityId) {
        return citiesService.updateCity(cityEntity, cityId);
    }

    @GetMapping
    public List<CityEntity> getAllCities() {
        return citiesService.getAllCities();
    }

    @GetMapping(path = "/{cityId}")
    CityEntity getUser(@PathVariable Integer cityId) {
        return citiesService.getCity(cityId);
    }

}
