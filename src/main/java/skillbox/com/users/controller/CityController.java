package skillbox.com.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.service.CitiesService;

import javax.swing.text.html.Option;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    private final CitiesService citiesService;

    public CityController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @PostMapping
    ResponseEntity<CityDto> CreateCity(@RequestBody CityDto cityDTO) {
        return new ResponseEntity<>(citiesService.createCity(cityDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{cityId}")
    ResponseEntity<Integer> deleteCity(@PathVariable Integer cityId) {
        boolean deleted = citiesService.deleteCity(cityId);

        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cityId, HttpStatus.OK);
    }

    @PatchMapping("/{cityId}")
    ResponseEntity<CityDto> UpdateCity(@RequestBody CityDto cityDto, @PathVariable Integer cityId) {
        boolean updated = citiesService.updateCity(cityDto, cityId);

        if (!updated) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCities() {
        return new ResponseEntity<>(citiesService.getAllCities(), HttpStatus.OK);
    }

    @GetMapping(path = "/{cityId}")
    ResponseEntity<CityDto> getCity(@PathVariable Integer cityId) {
        CityDto cityDto = citiesService.getCity(cityId);

        if (cityDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }

}
