package com.entidades.buenSabor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaClaseDTO extends BaseDto {
    private String denominacion;
    private Set<SucursalDto> sucursales = new HashSet<>();
    private Set<CategoriaHijoDto> subCategorias = new HashSet<>();
    private Set<ArticuloDto> articulos = new HashSet<>();
}
