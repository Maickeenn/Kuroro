package br.com.stratzord.kuroro.domain.parser;

import br.com.stratzord.kuroro.domain.dto.TypeDto;
import br.com.stratzord.kuroro.domain.model.Type;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeMapper {

  TypeMapper INSTANCE = Mappers.getMapper(TypeMapper.class);

  TypeDto typeToTypeDto(Type type);

}
