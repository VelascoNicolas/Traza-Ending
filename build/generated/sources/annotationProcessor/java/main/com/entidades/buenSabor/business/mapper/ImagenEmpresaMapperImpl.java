package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.ImagenDto;
import com.entidades.buenSabor.domain.entities.ImagenEmpresa;
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
public class ImagenEmpresaMapperImpl implements ImagenEmpresaMapper {

    @Override
    public ImagenDto toDTO(ImagenEmpresa source) {
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
    public ImagenEmpresa toEntity(ImagenDto source) {
        if ( source == null ) {
            return null;
        }

        ImagenEmpresa.ImagenEmpresaBuilder<?, ?> imagenEmpresa = ImagenEmpresa.builder();

        imagenEmpresa.id( source.getId() );
        imagenEmpresa.eliminado( source.isEliminado() );
        imagenEmpresa.fechaBaja( source.getFechaBaja() );
        imagenEmpresa.url( source.getUrl() );

        return imagenEmpresa.build();
    }

    @Override
    public List<ImagenDto> toDTOsList(List<ImagenEmpresa> source) {
        if ( source == null ) {
            return null;
        }

        List<ImagenDto> list = new ArrayList<ImagenDto>( source.size() );
        for ( ImagenEmpresa imagenEmpresa : source ) {
            list.add( toDTO( imagenEmpresa ) );
        }

        return list;
    }
}
