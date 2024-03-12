package br.com.stratzord.kuroro.domain.parser;

import br.com.stratzord.kuroro.domain.dto.MoveDto;
import br.com.stratzord.kuroro.domain.dto.TypeDto;
import br.com.stratzord.kuroro.domain.model.Move;
import br.com.stratzord.kuroro.domain.model.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.convert.TypeMapper;

@Mapper(uses = {TypeMapper.class})
public interface MoveMapper {

  MoveMapper INSTANCE = Mappers.getMapper(MoveMapper.class);

  @Mapping(target = "types", source = "types")
  MoveDto moveToMoveDto(Move move);

  TypeDto typeToTypeDto(Type type);

}
