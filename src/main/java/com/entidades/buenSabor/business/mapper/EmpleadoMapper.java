package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.EmpleadoDTO;
import com.entidades.buenSabor.domain.entities.Empleado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SucursalMapper.class})
public interface EmpleadoMapper extends BaseMapper<Empleado, EmpleadoDTO> {
    Empleado toEntity(EmpleadoDTO source);
    EmpleadoDTO toDTO(Empleado source);
}
