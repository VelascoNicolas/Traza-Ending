package com.entidades.buenSabor.domain.dto;


import lombok.*;


import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloDto extends BaseDto{
    protected String denominacion;
    protected Double precioVenta;
    protected Set<ImagenDto> imagenes = new HashSet<>();
    protected UnidadMedidaDto unidadMedida;
    protected CategoriaShortDTO categoria;
}
