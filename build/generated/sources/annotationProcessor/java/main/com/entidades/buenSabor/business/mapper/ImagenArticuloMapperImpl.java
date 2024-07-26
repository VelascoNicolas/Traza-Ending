package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.ImagenDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
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
public class ImagenArticuloMapperImpl implements ImagenArticuloMapper {

    @Override
    public ImagenDto toDTO(ImagenArticulo source) {
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
    public ImagenArticulo toEntity(ImagenDto source) {
        if ( source == null ) {
            return null;
        }

        ImagenArticulo.ImagenArticuloBuilder<?, ?> imagenArticulo = ImagenArticulo.builder();

        imagenArticulo.id( source.getId() );
        imagenArticulo.eliminado( source.isEliminado() );
        imagenArticulo.fechaBaja( source.getFechaBaja() );
        imagenArticulo.url( source.getUrl() );

        return imagenArticulo.build();
    }

    @Override
    public List<ImagenDto> toDTOsList(List<ImagenArticulo> source) {
        if ( source == null ) {
            return null;
        }

        List<ImagenDto> list = new ArrayList<ImagenDto>( source.size() );
        for ( ImagenArticulo imagenArticulo : source ) {
            list.add( toDTO( imagenArticulo ) );
        }

        return list;
    }
}
