package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.SucursalDto;
import com.entidades.buenSabor.domain.entities.Sucursal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-29T19:20:07-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class SucursalMapperImpl implements SucursalMapper {

    @Autowired
    private DomicilioMapper domicilioMapper;
    @Autowired
    private EmpresaMapper empresaMapper;

    @Override
    public SucursalDto toDTO(Sucursal source) {
        if ( source == null ) {
            return null;
        }

        SucursalDto sucursalDto = new SucursalDto();

        sucursalDto.setId( source.getId() );
        sucursalDto.setEliminado( source.isEliminado() );
        sucursalDto.setFechaBaja( source.getFechaBaja() );
        sucursalDto.setNombre( source.getNombre() );
        sucursalDto.setEsCasaMatriz( source.isEsCasaMatriz() );
        sucursalDto.setDomicilio( domicilioMapper.toDTO( source.getDomicilio() ) );
        sucursalDto.setEmpresa( empresaMapper.toDTO( source.getEmpresa() ) );

        return sucursalDto;
    }

    @Override
    public Sucursal toEntity(SucursalDto source) {
        if ( source == null ) {
            return null;
        }

        Sucursal.SucursalBuilder<?, ?> sucursal = Sucursal.builder();

        sucursal.id( source.getId() );
        sucursal.eliminado( source.isEliminado() );
        sucursal.fechaBaja( source.getFechaBaja() );
        sucursal.nombre( source.getNombre() );
        sucursal.esCasaMatriz( source.isEsCasaMatriz() );
        sucursal.domicilio( domicilioMapper.toEntity( source.getDomicilio() ) );
        sucursal.empresa( empresaMapper.toEntity( source.getEmpresa() ) );

        return sucursal.build();
    }

    @Override
    public List<SucursalDto> toDTOsList(List<Sucursal> source) {
        if ( source == null ) {
            return null;
        }

        List<SucursalDto> list = new ArrayList<SucursalDto>( source.size() );
        for ( Sucursal sucursal : source ) {
            list.add( toDTO( sucursal ) );
        }

        return list;
    }
}
