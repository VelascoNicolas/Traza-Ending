package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.UnidadMedida;
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
public class UnidadMedidaMapperImpl implements UnidadMedidaMapper {

    @Override
    public List<UnidadMedidaDto> toDTOsList(List<UnidadMedida> source) {
        if ( source == null ) {
            return null;
        }

        List<UnidadMedidaDto> list = new ArrayList<UnidadMedidaDto>( source.size() );
        for ( UnidadMedida unidadMedida : source ) {
            list.add( toDTO( unidadMedida ) );
        }

        return list;
    }

    @Override
    public UnidadMedida toEntity(UnidadMedidaDto source) {
        if ( source == null ) {
            return null;
        }

        UnidadMedida.UnidadMedidaBuilder<?, ?> unidadMedida = UnidadMedida.builder();

        unidadMedida.id( source.getId() );
        unidadMedida.eliminado( source.isEliminado() );
        unidadMedida.fechaBaja( source.getFechaBaja() );
        unidadMedida.denominacion( source.getDenominacion() );

        return unidadMedida.build();
    }

    @Override
    public UnidadMedidaDto toDTO(UnidadMedida source) {
        if ( source == null ) {
            return null;
        }

        UnidadMedidaDto unidadMedidaDto = new UnidadMedidaDto();

        unidadMedidaDto.setId( source.getId() );
        unidadMedidaDto.setEliminado( source.isEliminado() );
        unidadMedidaDto.setFechaBaja( source.getFechaBaja() );
        unidadMedidaDto.setDenominacion( source.getDenominacion() );

        return unidadMedidaDto;
    }
}
