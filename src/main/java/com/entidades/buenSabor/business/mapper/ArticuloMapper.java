package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.*;
import com.entidades.buenSabor.domain.entities.*;

import org.mapstruct.Mapper;
import org.mapstruct.Named;


import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring",uses = {ArticuloManufacturadoDetalleMapper.class, ImagenArticuloMapper.class, StockInsumoSucursalMapper.class, SucursalMapper.class})
public interface ArticuloMapper extends BaseMapper<Articulo,ArticuloDto> {

    @Named("toDto")
    ArticuloDto toDto(Articulo articulo);

    @Named("toDto")
    default ArticuloDto toDtoWithNamed(Articulo articulo) {
        return toDto(articulo);
    }

    Set<ArticuloDto> toDtoSet(Set<Articulo> articulos);

    default Articulo toEntity(ArticuloDto dto) {
        if (dto instanceof ArticuloInsumoDto) {
            return toEntity((ArticuloInsumoDto) dto);
        } else if (dto instanceof ArticuloManufacturadoDto) {
            return toEntity((ArticuloManufacturadoDto) dto);
        }
        // Aquí puedes manejar otros casos si es necesario
        return null;
    }

    ArticuloInsumo toEntity(ArticuloInsumoDto dto);
    ArticuloInsumoDto toDTO(ArticuloInsumo entity);
    ArticuloManufacturadoDto toDTO(ArticuloManufacturado entity);
    ArticuloManufacturado toEntity(ArticuloManufacturadoDto dto);
    List<ArticuloInsumoDto> toDtoListInsumo(List<ArticuloInsumo> articulosInsumos);
    List<ArticuloManufacturadoDto> toDtoListManufacturado(List<ArticuloManufacturado> articulosManufacturados);

}
