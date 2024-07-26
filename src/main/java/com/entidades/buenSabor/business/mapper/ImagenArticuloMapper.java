package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.ImagenDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenArticuloMapper extends BaseMapper<ImagenArticulo, ImagenDto>{
}
