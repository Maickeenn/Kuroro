package br.com.stratzord.kuroro.domain.parser;

import br.com.stratzord.kuroro.domain.dto.KuroroDto;
import br.com.stratzord.kuroro.domain.dto.TypeDto;
import br.com.stratzord.kuroro.domain.model.Kuroro;
import br.com.stratzord.kuroro.domain.model.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.convert.TypeMapper;

@Mapper(uses = {TypeMapper.class})
public interface KuroroMapper {

  KuroroMapper INSTANCE = Mappers.getMapper(KuroroMapper.class);

  @Mapping(target = "types", source = "types")
  KuroroDto kuroroToKuroroDto(Kuroro kuroro);

  TypeDto typeToTypeDto(Type type);

}
