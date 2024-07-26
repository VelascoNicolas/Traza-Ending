package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Audited
public class ArticuloInsumo extends Articulo{
    private Double precioCompra;
    private Boolean esParaElaborar;

    @OneToMany(mappedBy = "articuloInsumo",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<StockInsumoSucursal> stocksInsumo=new HashSet<>();
}
