package com.entidades.buenSabor.domain.dto;


import lombok.*;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloManufacturadoDto extends ArticuloDto{
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;
    private Set<ArticuloManufacturadoDetalleDto> articuloManufacturadoDetalles = new HashSet<>();
    private Set<SucursalShortDTO> sucursales = new HashSet<>();

}
