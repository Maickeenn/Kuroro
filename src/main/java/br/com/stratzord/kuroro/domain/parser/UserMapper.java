package br.com.stratzord.kuroro.domain.parser;

import br.com.stratzord.kuroro.domain.dto.UserDto;
import br.com.stratzord.kuroro.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDto userToUserDto(User user);

}
