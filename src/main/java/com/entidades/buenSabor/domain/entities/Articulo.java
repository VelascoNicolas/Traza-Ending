package com.entidades.buenSabor.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Audited
public abstract class Articulo extends Base {
    protected String denominacion;
    protected Double precioVenta;

    @OneToMany(cascade = CascadeType.ALL)
    //SE AGREGA EL JOIN COLUMN PARA QUE JPA NO CREE LA TABLA INTERMEDIA EN UNA RELACION ONE TO MANY
    //DE ESTA MANERA PONE EL FOREIGN KEY 'cliente_id' EN LA TABLA DE LOS MANY
    @JoinColumn(name = "articulo_id")
    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
    @NotAudited
    @Builder.Default
    protected Set<ImagenArticulo> imagenes = new HashSet<>();

    @ManyToOne
    protected UnidadMedida unidadMedida;

    @ManyToOne
    protected Categoria categoria;
}

