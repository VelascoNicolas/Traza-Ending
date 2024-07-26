package com.entidades.buenSabor.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class DetallePedidoDTO {
    private Long id;
    private boolean eliminado;
    private Integer cantidad;
    private Double subTotal;
    private Long articulo;
    private Long promocion;
}
