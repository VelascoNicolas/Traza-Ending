package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.LocalidadDto;
import com.entidades.buenSabor.domain.dto.PaisDto;
import com.entidades.buenSabor.domain.dto.ProvinciaDto;
import com.entidades.buenSabor.domain.entities.Localidad;
import com.entidades.buenSabor.domain.entities.Pais;
import com.entidades.buenSabor.domain.entities.Provincia;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-23T18:32:10-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class LocalidadMapperImpl implements LocalidadMapper {

    @Override
    public LocalidadDto toDTO(Localidad source) {
        if ( source == null ) {
            return null;
        }

        LocalidadDto localidadDto = new LocalidadDto();

        localidadDto.setId( source.getId() );
        localidadDto.setEliminado( source.isEliminado() );
        localidadDto.setFechaBaja( source.getFechaBaja() );
        localidadDto.setNombre( source.getNombre() );
        localidadDto.setProvincia( provinciaToProvinciaDto( source.getProvincia() ) );

        return localidadDto;
    }

    @Override
    public Localidad toEntity(LocalidadDto source) {
        if ( source == null ) {
            return null;
        }

        Localidad.LocalidadBuilder<?, ?> localidad = Localidad.builder();

        localidad.id( source.getId() );
        localidad.eliminado( source.isEliminado() );
        localidad.fechaBaja( source.getFechaBaja() );
        localidad.nombre( source.getNombre() );
        localidad.provincia( provinciaDtoToProvincia( source.getProvincia() ) );

        return localidad.build();
    }

    @Override
    public List<LocalidadDto> toDTOsList(List<Localidad> source) {
        if ( source == null ) {
            return null;
        }

        List<LocalidadDto> list = new ArrayList<LocalidadDto>( source.size() );
        for ( Localidad localidad : source ) {
            list.add( toDTO( localidad ) );
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

    protected ProvinciaDto provinciaToProvinciaDto(Provincia provincia) {
        if ( provincia == null ) {
            return null;
        }

        ProvinciaDto provinciaDto = new ProvinciaDto();

        provinciaDto.setId( provincia.getId() );
        provinciaDto.setEliminado( provincia.isEliminado() );
        provinciaDto.setFechaBaja( provincia.getFechaBaja() );
        provinciaDto.setNombre( provincia.getNombre() );
        provinciaDto.setPais( paisToPaisDto( provincia.getPais() ) );

        return provinciaDto;
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

    protected Provincia provinciaDtoToProvincia(ProvinciaDto provinciaDto) {
        if ( provinciaDto == null ) {
            return null;
        }

        Provincia.ProvinciaBuilder<?, ?> provincia = Provincia.builder();

        provincia.id( provinciaDto.getId() );
        provincia.eliminado( provinciaDto.isEliminado() );
        provincia.fechaBaja( provinciaDto.getFechaBaja() );
        provincia.nombre( provinciaDto.getNombre() );
        provincia.pais( paisDtoToPais( provinciaDto.getPais() ) );

        return provincia.build();
    }
}
