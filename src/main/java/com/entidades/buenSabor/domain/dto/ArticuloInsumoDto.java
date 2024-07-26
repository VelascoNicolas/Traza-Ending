package com.entidades.buenSabor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloInsumoDto extends ArticuloDto{
    private Double precioCompra;
    private Boolean esParaElaborar;
    private Set<StockInsumoSucursalDTO> stocksInsumo;

}
