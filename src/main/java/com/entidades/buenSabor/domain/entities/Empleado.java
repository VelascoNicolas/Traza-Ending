package com.entidades.buenSabor.domain.entities;

import com.entidades.buenSabor.domain.enums.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Audited
public class Empleado extends Base{
    private boolean activo;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    private Set<Pedido> pedidos= new HashSet<>();

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
}
