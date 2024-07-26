package com.entidades.buenSabor.domain.dto;

import com.entidades.buenSabor.domain.entities.*;
import com.entidades.buenSabor.domain.enums.Estado;
import com.entidades.buenSabor.domain.enums.FormaPago;
import com.entidades.buenSabor.domain.enums.TipoEnvio;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class PedidoDTO {
    private Long id;
    private boolean eliminado;
    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    private Domicilio domicilio;

    private Sucursal sucursal;

    private Factura factura;

    private Cliente cliente;

    @Builder.Default
    private Set<DetallePedidoDTO> detallePedidos = new HashSet<>();

    private Empleado empleado;
}
