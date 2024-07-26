package com.entidades.buenSabor.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
@Audited
public class Empresa extends Base{

    private String nombre;
    private String razonSocial;
    @Column(name = "cuil", unique = true)
    private Long cuil;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "empresa_id")
    @NotAudited
    private Set<ImagenEmpresa> imagenes;


    @OneToMany(mappedBy = "empresa",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private Set<Sucursal> sucursales= new HashSet<>();

}
