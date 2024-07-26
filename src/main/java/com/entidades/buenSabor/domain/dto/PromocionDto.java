package com.entidades.buenSabor.domain.dto;

import com.entidades.buenSabor.domain.entities.ImagenPromocion;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.domain.enums.TipoPromocion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.envers.NotAudited;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PromocionDto extends BaseDto {
    private String denominacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private LocalTime horaDesde;
    private LocalTime horaHasta;
    private String descripcionDescuento;
    private Double precioPromocional;
    private TipoPromocion tipoPromocion;



    private Set<PromocionDetalleDto> promocionDetalles = new HashSet<>();


    private Set<ImagenDto> imagenes = new HashSet<>();


    private Set<Long> sucursalesId = new HashSet<>();

}
