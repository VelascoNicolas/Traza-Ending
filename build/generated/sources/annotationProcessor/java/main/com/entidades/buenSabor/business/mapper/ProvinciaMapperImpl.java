package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.PaisDto;
import com.entidades.buenSabor.domain.dto.ProvinciaDto;
import com.entidades.buenSabor.domain.entities.Pais;
import com.entidades.buenSabor.domain.entities.Provincia;
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
public class ProvinciaMapperImpl implements ProvinciaMapper {

    @Override
    public ProvinciaDto toDTO(Provincia source) {
        if ( source == null ) {
            return null;
        }

        ProvinciaDto provinciaDto = new ProvinciaDto();

        provinciaDto.setId( source.getId() );
        provinciaDto.setEliminado( source.isEliminado() );
        provinciaDto.setFechaBaja( source.getFechaBaja() );
        provinciaDto.setNombre( source.getNombre() );
        provinciaDto.setPais( paisToPaisDto( source.getPais() ) );

        return provinciaDto;
    }

    @Override
    public Provincia toEntity(ProvinciaDto source) {
        if ( source == null ) {
            return null;
        }

        Provincia.ProvinciaBuilder<?, ?> provincia = Provincia.builder();

        provincia.id( source.getId() );
        provincia.eliminado( source.isEliminado() );
        provincia.fechaBaja( source.getFechaBaja() );
        provincia.nombre( source.getNombre() );
        provincia.pais( paisDtoToPais( source.getPais() ) );

        return provincia.build();
    }

    @Override
    public List<ProvinciaDto> toDTOsList(List<Provincia> source) {
        if ( source == null ) {
            return null;
        }

        List<ProvinciaDto> list = new ArrayList<ProvinciaDto>( source.size() );
        for ( Provincia provincia : source ) {
            list.add( toDTO( provincia ) );
        }

        return list;
    }

    protected PaisDto paisToPaisDto(Pais pais) {
        if ( pais == null ) {
            return null;
        }

        PaisDto paisDto = new PaisDto();

        paisDto.setEliminado( pais.isEliminado() );
        paisDto.setFechaBaja( pais.getFechaBaja() );
        paisDto.setId( pais.getId() );
        paisDto.setNombre( pais.getNombre() );

        return paisDto;
    }

    protected Pais paisDtoToPais(PaisDto paisDto) {
        if ( paisDto == null ) {
            return null;
        }

        Pais.PaisBuilder<?, ?> pais = Pais.builder();

        pais.id( paisDto.getId() );
        pais.eliminado( paisDto.isEliminado() );
        pais.fechaBaja( paisDto.getFechaBaja() );
        pais.nombre( paisDto.getNombre() );

        return pais.build();
    }
}
