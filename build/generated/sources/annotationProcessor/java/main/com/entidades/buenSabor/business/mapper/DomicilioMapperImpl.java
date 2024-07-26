package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.DomicilioDto;
import com.entidades.buenSabor.domain.entities.Domicilio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-23T18:32:09-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class DomicilioMapperImpl implements DomicilioMapper {

    @Autowired
    private LocalidadMapper localidadMapper;

    @Override
    public DomicilioDto toDTO(Domicilio source) {
        if ( source == null ) {
            return null;
        }

        DomicilioDto domicilioDto = new DomicilioDto();

        domicilioDto.setId( source.getId() );
        domicilioDto.setEliminado( source.isEliminado() );
        domicilioDto.setFechaBaja( source.getFechaBaja() );
        domicilioDto.setCalle( source.getCalle() );
        domicilioDto.setNumero( source.getNumero() );
        domicilioDto.setCp( source.getCp() );
        domicilioDto.setPiso( source.getPiso() );
        domicilioDto.setNroDpto( source.getNroDpto() );
        domicilioDto.setLocalidad( localidadMapper.toDTO( source.getLocalidad() ) );

        return domicilioDto;
    }

    @Override
    public Domicilio toEntity(DomicilioDto source) {
        if ( source == null ) {
            return null;
        }

        Domicilio.DomicilioBuilder<?, ?> domicilio = Domicilio.builder();

        domicilio.id( source.getId() );
        domicilio.eliminado( source.isEliminado() );
        domicilio.fechaBaja( source.getFechaBaja() );
        domicilio.calle( source.getCalle() );
        domicilio.numero( source.getNumero() );
        domicilio.cp( source.getCp() );
        domicilio.piso( source.getPiso() );
        domicilio.nroDpto( source.getNroDpto() );
        domicilio.localidad( localidadMapper.toEntity( source.getLocalidad() ) );

        return domicilio.build();
    }

    @Override
    public List<DomicilioDto> toDTOsList(List<Domicilio> source) {
        if ( source == null ) {
            return null;
        }

        List<DomicilioDto> list = new ArrayList<DomicilioDto>( source.size() );
        for ( Domicilio domicilio : source ) {
            list.add( toDTO( domicilio ) );
        }

        return list;
    }
}
