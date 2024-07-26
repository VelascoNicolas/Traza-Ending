package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.ImagenDto;
import com.entidades.buenSabor.domain.entities.ImagenPromocion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenPromocionMapper extends BaseMapper<ImagenPromocion, ImagenDto>{
}
