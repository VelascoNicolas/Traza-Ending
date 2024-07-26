package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.EmpleadoDTO;
import com.entidades.buenSabor.domain.entities.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-26T17:52:56-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class EmpleadoMapperImpl implements EmpleadoMapper {

    @Autowired
    private SucursalMapper sucursalMapper;

    @Override
    public List<EmpleadoDTO> toDTOsList(List<Empleado> source) {
        if ( source == null ) {
            return null;
        }

        List<EmpleadoDTO> list = new ArrayList<EmpleadoDTO>( source.size() );
        for ( Empleado empleado : source ) {
            list.add( toDTO( empleado ) );
        }

        return list;
    }

    @Override
    public Empleado toEntity(EmpleadoDTO source) {
        if ( source == null ) {
            return null;
        }

        Empleado.EmpleadoBuilder<?, ?> empleado = Empleado.builder();

        empleado.id( source.getId() );
        empleado.eliminado( source.isEliminado() );
        empleado.fechaBaja( source.getFechaBaja() );
        empleado.activo( source.isActivo() );
        empleado.nombre( source.getNombre() );
        empleado.apellido( source.getApellido() );
        empleado.telefono( source.getTelefono() );
        empleado.email( source.getEmail() );
        empleado.rol( source.getRol() );
        empleado.fechaNacimiento( source.getFechaNacimiento() );
        empleado.sucursal( sucursalMapper.toEntity( source.getSucursal() ) );

        return empleado.build();
    }

    @Override
    public EmpleadoDTO toDTO(Empleado source) {
        if ( source == null ) {
            return null;
        }

        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        empleadoDTO.setId( source.getId() );
        empleadoDTO.setEliminado( source.isEliminado() );
        empleadoDTO.setFechaBaja( source.getFechaBaja() );
        empleadoDTO.setActivo( source.isActivo() );
        empleadoDTO.setNombre( source.getNombre() );
        empleadoDTO.setApellido( source.getApellido() );
        empleadoDTO.setTelefono( source.getTelefono() );
        empleadoDTO.setEmail( source.getEmail() );
        empleadoDTO.setRol( source.getRol() );
        empleadoDTO.setFechaNacimiento( source.getFechaNacimiento() );
        empleadoDTO.setSucursal( sucursalMapper.toDTO( source.getSucursal() ) );

        return empleadoDTO;
    }
}
