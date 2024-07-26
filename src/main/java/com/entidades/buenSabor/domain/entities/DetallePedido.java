package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Audited
public class DetallePedido extends Base{
    private Integer cantidad;
    private Double subTotal;

    @ManyToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

    public Double calcularSubTotal() {
        Double subtotal = 0.0;
        if (this.getArticulo() != null) {
            if (this.getArticulo() instanceof ArticuloInsumo) {
                subtotal += this.getCantidad() * ((ArticuloInsumo) this.getArticulo()).getPrecioVenta();
            } else if (this.getArticulo() instanceof ArticuloManufacturado) {
                subtotal += this.getCantidad() * ((ArticuloManufacturado) this.getArticulo()).getPrecioVenta();
            }
        }
        if (this.getPromocion() != null) {
            subtotal += promocion.getPrecioPromocional() * this.getCantidad();
        }
        this.setSubTotal(subtotal);
        return subtotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        if (articulo instanceof ArticuloInsumo) {
            this.articulo = (ArticuloInsumo) articulo;
        } else if (articulo instanceof ArticuloManufacturado) {
            this.articulo = (ArticuloManufacturado) articulo;
        }
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }
}
