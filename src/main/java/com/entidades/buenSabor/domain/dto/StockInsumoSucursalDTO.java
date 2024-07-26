package com.entidades.buenSabor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockInsumoSucursalDTO extends BaseDto {
    private Double stockActual;
    private Double stockMinimo;
    private Double stockMaximo;
    private SucursalShortDTO sucursal;
}
