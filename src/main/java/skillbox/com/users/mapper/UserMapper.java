package skillbox.com.users.mapper;

import org.springframework.stereotype.Component;
import skillbox.com.users.dto.CityDto;
import skillbox.com.users.dto.UserDto;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.entity.UserEntity;

@Component
public class UserMapper {
    public static UserEntity dtoToEntity (UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new UserEntity(
                userDto.getId(),
                userDto.getName(),
                userDto.getLogin(),
                userDto.getGender(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.isActive(),
                userDto.getAddress(),
                userDto.isDeleted(),
                userDto.getCityId()
        );
    }

    public static UserDto entityToDto(UserEntity userEntity) {
        if (userEntity == null) {
            return  null;
        }
        return new UserDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLogin(),
                userEntity.getGender(),
                userEntity.getEmail(),
                userEntity.getPhone(),
                userEntity.isActive(),
                userEntity.getAddress(),
                userEntity.isDeleted(),
                userEntity.getCityId()
        );
    }
}
