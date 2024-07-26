package com.entidades.buenSabor.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Audited
public class StockInsumoSucursal extends Base {
    private Double stockActual;
    private Double stockMinimo;
    private Double stockMaximo;
    @ManyToOne
    @JoinColumn(name="articuloInsumo")
    @JsonIgnore
    private ArticuloInsumo articuloInsumo;

    @ManyToOne
    @JoinColumn(name="sucursal")
    @JsonIgnore
    private Sucursal sucursal;
}
