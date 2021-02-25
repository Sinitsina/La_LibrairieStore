package ru.lalibrairiestore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.lalibrairiestore.dto.UserDTO;
import ru.lalibrairiestore.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDto(User user);

}
