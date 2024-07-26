package com.entidades.buenSabor.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@Audited
public class Sucursal extends  Base{

    private String nombre;
    private final String horariosAtencion = "Lunes a Domingos de 20:00 a 12:00\n" +
                                      "Y Sábados y Domingos de 11:00 a 15:00";
    private boolean esCasaMatriz;

    @OneToOne(cascade = CascadeType.ALL)
    private Domicilio domicilio;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinTable(name = "sucursal_promocion",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "promocion_id"))
    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
    @Builder.Default
    @JsonIgnore
    private Set<Promocion> promociones = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @ToString.Exclude
    //SE AGREGA EL JOIN TABLE PARA QUE JPA CREE LA TABLA INTERMEDIA
    // EN UNA RELACION MANY TO MANY
    @JoinTable(name = "sucursal_categoria",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
    @Builder.Default
    @JsonIgnore
    private Set<Categoria> categorias = new HashSet<>();

    @OneToMany(mappedBy = "sucursal",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @Builder.Default
    @JsonIgnore
    private Set<Empleado> empleados = new HashSet<>();

    @ManyToOne
    private Empresa empresa;

    @OneToMany(mappedBy = "sucursal",fetch = FetchType.LAZY)
    @Builder.Default
    @JsonIgnore
    private Set<StockInsumoSucursal> stocksSucursal=new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinTable(name = "sucursal_manufacturado",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "articuloManufacturado_id"))
    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
    @Builder.Default
    @JsonIgnore
    private Set<ArticuloManufacturado> manufacturados = new HashSet<>();
}
