package skillbox.com.users.mapper;

import org.springframework.stereotype.Component;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.entity.CityEntity;

@Component
public class CityMapper {
    public CityEntity dtoToEntity (CityDto cityDto) {
        if (cityDto == null) {
            return null;
        }
        return new CityEntity(cityDto.getId(), cityDto.getName());
    }

    public CityDto entityToDto(CityEntity cityEntity) {
        if (cityEntity == null) {
            return  null;
        }
        return new CityDto(cityEntity.getId(), cityEntity.getName());
    }
}
