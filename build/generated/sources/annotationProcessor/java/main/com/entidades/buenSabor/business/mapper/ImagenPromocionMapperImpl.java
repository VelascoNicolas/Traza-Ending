package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.ImagenDto;
import com.entidades.buenSabor.domain.entities.ImagenPromocion;
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
public class ImagenPromocionMapperImpl implements ImagenPromocionMapper {

    @Override
    public ImagenDto toDTO(ImagenPromocion source) {
        if ( source == null ) {
            return null;
        }

        ImagenDto imagenDto = new ImagenDto();

        imagenDto.setId( source.getId() );
        imagenDto.setEliminado( source.isEliminado() );
        imagenDto.setFechaBaja( source.getFechaBaja() );
        imagenDto.setUrl( source.getUrl() );

        return imagenDto;
    }

    @Override
    public ImagenPromocion toEntity(ImagenDto source) {
        if ( source == null ) {
            return null;
        }

        ImagenPromocion.ImagenPromocionBuilder<?, ?> imagenPromocion = ImagenPromocion.builder();

        imagenPromocion.id( source.getId() );
        imagenPromocion.eliminado( source.isEliminado() );
        imagenPromocion.fechaBaja( source.getFechaBaja() );
        imagenPromocion.url( source.getUrl() );

        return imagenPromocion.build();
    }

    @Override
    public List<ImagenDto> toDTOsList(List<ImagenPromocion> source) {
        if ( source == null ) {
            return null;
        }

        List<ImagenDto> list = new ArrayList<ImagenDto>( source.size() );
        for ( ImagenPromocion imagenPromocion : source ) {
            list.add( toDTO( imagenPromocion ) );
        }

        return list;
    }
}
