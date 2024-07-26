package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.PaisDto;
import com.entidades.buenSabor.domain.entities.Pais;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-23T18:32:09-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class PaisMapperImpl implements PaisMapper {

    @Override
    public PaisDto toDTO(Pais source) {
        if ( source == null ) {
            return null;
        }

        PaisDto paisDto = new PaisDto();

        paisDto.setEliminado( source.isEliminado() );
        paisDto.setFechaBaja( source.getFechaBaja() );
        paisDto.setId( source.getId() );
        paisDto.setNombre( source.getNombre() );

        return paisDto;
    }

    @Override
    public Pais toEntity(PaisDto source) {
        if ( source == null ) {
            return null;
        }

        Pais.PaisBuilder<?, ?> pais = Pais.builder();

        pais.id( source.getId() );
        pais.eliminado( source.isEliminado() );
        pais.fechaBaja( source.getFechaBaja() );
        pais.nombre( source.getNombre() );

        return pais.build();
    }

    @Override
    public List<PaisDto> toDTOsList(List<Pais> source) {
        if ( source == null ) {
            return null;
        }

        List<PaisDto> list = new ArrayList<PaisDto>( source.size() );
        for ( Pais pais : source ) {
            list.add( toDTO( pais ) );
        }

        return list;
    }
}
