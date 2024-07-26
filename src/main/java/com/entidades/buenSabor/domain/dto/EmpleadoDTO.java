package com.entidades.buenSabor.domain.dto;

import com.entidades.buenSabor.domain.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmpleadoDTO extends BaseDto {
    private boolean activo;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Rol rol;
    private LocalDate fechaNacimiento;

    private SucursalDto sucursal;
}
