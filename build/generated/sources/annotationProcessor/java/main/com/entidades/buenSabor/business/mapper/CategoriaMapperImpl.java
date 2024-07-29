package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.CategoriaHijoDto;
import com.entidades.buenSabor.domain.dto.CategoriaPadreDto;
import com.entidades.buenSabor.domain.dto.SucursalDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.Sucursal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-29T19:20:07-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class CategoriaMapperImpl implements CategoriaMapper {

    @Autowired
    private SucursalMapper sucursalMapper;

    @Override
    public CategoriaPadreDto toDTO(Categoria source) {
        if ( source == null ) {
            return null;
        }

        CategoriaPadreDto categoriaPadreDto = new CategoriaPadreDto();

        categoriaPadreDto.setId( source.getId() );
        categoriaPadreDto.setEliminado( source.isEliminado() );
        categoriaPadreDto.setFechaBaja( source.getFechaBaja() );
        categoriaPadreDto.setDenominacion( source.getDenominacion() );
        categoriaPadreDto.setSucursales( sucursalSetToSucursalDtoSet( source.getSucursales() ) );
        categoriaPadreDto.setSubCategorias( categoriaSetToCategoriaHijoDtoSet( source.getSubCategorias() ) );

        return categoriaPadreDto;
    }

    @Override
    public List<CategoriaPadreDto> toDTOsList(List<Categoria> source) {
        if ( source == null ) {
            return null;
        }

        List<CategoriaPadreDto> list = new ArrayList<CategoriaPadreDto>( source.size() );
        for ( Categoria categoria : source ) {
            list.add( toDTO( categoria ) );
        }

        return list;
    }

    @Override
    public Categoria toEntity(CategoriaPadreDto source) {
        if ( source == null ) {
            return null;
        }

        Categoria.CategoriaBuilder<?, ?> categoria = Categoria.builder();

        categoria.id( source.getId() );
        categoria.eliminado( source.isEliminado() );
        categoria.fechaBaja( source.getFechaBaja() );
        categoria.denominacion( source.getDenominacion() );
        categoria.sucursales( sucursalDtoSetToSucursalSet( source.getSucursales() ) );
        categoria.subCategorias( categoriaHijoDtoSetToCategoriaSet( source.getSubCategorias() ) );

        return categoria.build();
    }

    @Override
    public Categoria aEntidad(CategoriaHijoDto source) {
        if ( source == null ) {
            return null;
        }

        Categoria.CategoriaBuilder<?, ?> categoria = Categoria.builder();

        categoria.id( source.getId() );
        categoria.eliminado( source.isEliminado() );
        categoria.fechaBaja( source.getFechaBaja() );
        categoria.denominacion( source.getDenominacion() );
        categoria.sucursales( sucursalDtoSetToSucursalSet( source.getSucursales() ) );

        return categoria.build();
    }

    @Override
    public CategoriaHijoDto toShortDTO(Categoria source) {
        if ( source == null ) {
            return null;
        }

        CategoriaHijoDto categoriaHijoDto = new CategoriaHijoDto();

        categoriaHijoDto.setId( source.getId() );
        categoriaHijoDto.setEliminado( source.isEliminado() );
        categoriaHijoDto.setFechaBaja( source.getFechaBaja() );
        categoriaHijoDto.setDenominacion( source.getDenominacion() );
        categoriaHijoDto.setSucursales( sucursalSetToSucursalDtoSet( source.getSucursales() ) );

        return categoriaHijoDto;
    }

    protected Set<SucursalDto> sucursalSetToSucursalDtoSet(Set<Sucursal> set) {
        if ( set == null ) {
            return null;
        }

        Set<SucursalDto> set1 = new LinkedHashSet<SucursalDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Sucursal sucursal : set ) {
            set1.add( sucursalMapper.toDTO( sucursal ) );
        }

        return set1;
    }

    protected Set<CategoriaHijoDto> categoriaSetToCategoriaHijoDtoSet(Set<Categoria> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoriaHijoDto> set1 = new LinkedHashSet<CategoriaHijoDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Categoria categoria : set ) {
            set1.add( toShortDTO( categoria ) );
        }

        return set1;
    }

    protected Set<Sucursal> sucursalDtoSetToSucursalSet(Set<SucursalDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Sucursal> set1 = new LinkedHashSet<Sucursal>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( SucursalDto sucursalDto : set ) {
            set1.add( sucursalMapper.toEntity( sucursalDto ) );
        }

        return set1;
    }

    protected Set<Categoria> categoriaHijoDtoSetToCategoriaSet(Set<CategoriaHijoDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Categoria> set1 = new LinkedHashSet<Categoria>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoriaHijoDto categoriaHijoDto : set ) {
            set1.add( aEntidad( categoriaHijoDto ) );
        }

        return set1;
    }
}
