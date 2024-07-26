package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.CategoriaClaseDTO;
import com.entidades.buenSabor.domain.dto.CategoriaHijoDto;
import com.entidades.buenSabor.domain.dto.CategoriaPadreDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ArticuloMapper.class, SucursalMapper.class})
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaPadreDto>{

    Categoria toEntity(CategoriaPadreDto source);
    Categoria aEntidad(CategoriaHijoDto source);
    CategoriaHijoDto toShortDTO(Categoria source);
}
