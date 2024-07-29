package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.ArticuloDto;
import com.entidades.buenSabor.domain.dto.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.CategoriaShortDTO;
import com.entidades.buenSabor.domain.dto.ImagenDto;
import com.entidades.buenSabor.domain.dto.StockInsumoSucursalDTO;
import com.entidades.buenSabor.domain.dto.SucursalShortDTO;
import com.entidades.buenSabor.domain.dto.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.domain.entities.StockInsumoSucursal;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.domain.entities.UnidadMedida;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-29T19:20:08-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class ArticuloMapperImpl implements ArticuloMapper {

    @Autowired
    private ArticuloManufacturadoDetalleMapper articuloManufacturadoDetalleMapper;
    @Autowired
    private ImagenArticuloMapper imagenArticuloMapper;
    @Autowired
    private StockInsumoSucursalMapper stockInsumoSucursalMapper;

    @Override
    public ArticuloDto toDTO(Articulo source) {
        if ( source == null ) {
            return null;
        }

        ArticuloDto articuloDto = new ArticuloDto();

        articuloDto.setId( source.getId() );
        articuloDto.setEliminado( source.isEliminado() );
        articuloDto.setFechaBaja( source.getFechaBaja() );
        articuloDto.setDenominacion( source.getDenominacion() );
        articuloDto.setPrecioVenta( source.getPrecioVenta() );
        articuloDto.setImagenes( imagenArticuloSetToImagenDtoSet( source.getImagenes() ) );
        articuloDto.setUnidadMedida( unidadMedidaToUnidadMedidaDto( source.getUnidadMedida() ) );
        articuloDto.setCategoria( categoriaToCategoriaShortDTO( source.getCategoria() ) );

        return articuloDto;
    }

    @Override
    public List<ArticuloDto> toDTOsList(List<Articulo> source) {
        if ( source == null ) {
            return null;
        }

        List<ArticuloDto> list = new ArrayList<ArticuloDto>( source.size() );
        for ( Articulo articulo : source ) {
            list.add( toDTO( articulo ) );
        }

        return list;
    }

    @Override
    public ArticuloDto toDto(Articulo articulo) {
        if ( articulo == null ) {
            return null;
        }

        ArticuloDto articuloDto = new ArticuloDto();

        articuloDto.setId( articulo.getId() );
        articuloDto.setEliminado( articulo.isEliminado() );
        articuloDto.setFechaBaja( articulo.getFechaBaja() );
        articuloDto.setDenominacion( articulo.getDenominacion() );
        articuloDto.setPrecioVenta( articulo.getPrecioVenta() );
        articuloDto.setImagenes( imagenArticuloSetToImagenDtoSet( articulo.getImagenes() ) );
        articuloDto.setUnidadMedida( unidadMedidaToUnidadMedidaDto( articulo.getUnidadMedida() ) );
        articuloDto.setCategoria( categoriaToCategoriaShortDTO( articulo.getCategoria() ) );

        return articuloDto;
    }

    @Override
    public Set<ArticuloDto> toDtoSet(Set<Articulo> articulos) {
        if ( articulos == null ) {
            return null;
        }

        Set<ArticuloDto> set = new LinkedHashSet<ArticuloDto>( Math.max( (int) ( articulos.size() / .75f ) + 1, 16 ) );
        for ( Articulo articulo : articulos ) {
            set.add( toDTO( articulo ) );
        }

        return set;
    }

    @Override
    public ArticuloInsumo toEntity(ArticuloInsumoDto dto) {
        if ( dto == null ) {
            return null;
        }

        ArticuloInsumo.ArticuloInsumoBuilder<?, ?> articuloInsumo = ArticuloInsumo.builder();

        articuloInsumo.id( dto.getId() );
        articuloInsumo.eliminado( dto.isEliminado() );
        articuloInsumo.fechaBaja( dto.getFechaBaja() );
        articuloInsumo.denominacion( dto.getDenominacion() );
        articuloInsumo.precioVenta( dto.getPrecioVenta() );
        articuloInsumo.imagenes( imagenDtoSetToImagenArticuloSet( dto.getImagenes() ) );
        articuloInsumo.unidadMedida( unidadMedidaDtoToUnidadMedida( dto.getUnidadMedida() ) );
        articuloInsumo.categoria( categoriaShortDTOToCategoria( dto.getCategoria() ) );
        articuloInsumo.precioCompra( dto.getPrecioCompra() );
        articuloInsumo.esParaElaborar( dto.getEsParaElaborar() );
        articuloInsumo.stocksInsumo( stockInsumoSucursalDTOSetToStockInsumoSucursalSet( dto.getStocksInsumo() ) );

        return articuloInsumo.build();
    }

    @Override
    public ArticuloInsumoDto toDTO(ArticuloInsumo entity) {
        if ( entity == null ) {
            return null;
        }

        ArticuloInsumoDto articuloInsumoDto = new ArticuloInsumoDto();

        articuloInsumoDto.setId( entity.getId() );
        articuloInsumoDto.setEliminado( entity.isEliminado() );
        articuloInsumoDto.setFechaBaja( entity.getFechaBaja() );
        articuloInsumoDto.setDenominacion( entity.getDenominacion() );
        articuloInsumoDto.setPrecioVenta( entity.getPrecioVenta() );
        articuloInsumoDto.setImagenes( imagenArticuloSetToImagenDtoSet( entity.getImagenes() ) );
        articuloInsumoDto.setUnidadMedida( unidadMedidaToUnidadMedidaDto( entity.getUnidadMedida() ) );
        articuloInsumoDto.setCategoria( categoriaToCategoriaShortDTO( entity.getCategoria() ) );
        articuloInsumoDto.setPrecioCompra( entity.getPrecioCompra() );
        articuloInsumoDto.setEsParaElaborar( entity.getEsParaElaborar() );
        articuloInsumoDto.setStocksInsumo( stockInsumoSucursalSetToStockInsumoSucursalDTOSet( entity.getStocksInsumo() ) );

        return articuloInsumoDto;
    }

    @Override
    public ArticuloManufacturadoDto toDTO(ArticuloManufacturado entity) {
        if ( entity == null ) {
            return null;
        }

        ArticuloManufacturadoDto articuloManufacturadoDto = new ArticuloManufacturadoDto();

        articuloManufacturadoDto.setId( entity.getId() );
        articuloManufacturadoDto.setEliminado( entity.isEliminado() );
        articuloManufacturadoDto.setFechaBaja( entity.getFechaBaja() );
        articuloManufacturadoDto.setDenominacion( entity.getDenominacion() );
        articuloManufacturadoDto.setPrecioVenta( entity.getPrecioVenta() );
        articuloManufacturadoDto.setImagenes( imagenArticuloSetToImagenDtoSet( entity.getImagenes() ) );
        articuloManufacturadoDto.setUnidadMedida( unidadMedidaToUnidadMedidaDto( entity.getUnidadMedida() ) );
        articuloManufacturadoDto.setCategoria( categoriaToCategoriaShortDTO( entity.getCategoria() ) );
        articuloManufacturadoDto.setDescripcion( entity.getDescripcion() );
        articuloManufacturadoDto.setTiempoEstimadoMinutos( entity.getTiempoEstimadoMinutos() );
        articuloManufacturadoDto.setPreparacion( entity.getPreparacion() );
        articuloManufacturadoDto.setArticuloManufacturadoDetalles( articuloManufacturadoDetalleSetToArticuloManufacturadoDetalleDtoSet( entity.getArticuloManufacturadoDetalles() ) );
        articuloManufacturadoDto.setSucursales( sucursalSetToSucursalShortDTOSet( entity.getSucursales() ) );

        return articuloManufacturadoDto;
    }

    @Override
    public ArticuloManufacturado toEntity(ArticuloManufacturadoDto dto) {
        if ( dto == null ) {
            return null;
        }

        ArticuloManufacturado.ArticuloManufacturadoBuilder<?, ?> articuloManufacturado = ArticuloManufacturado.builder();

        articuloManufacturado.id( dto.getId() );
        articuloManufacturado.eliminado( dto.isEliminado() );
        articuloManufacturado.fechaBaja( dto.getFechaBaja() );
        articuloManufacturado.denominacion( dto.getDenominacion() );
        articuloManufacturado.precioVenta( dto.getPrecioVenta() );
        articuloManufacturado.imagenes( imagenDtoSetToImagenArticuloSet( dto.getImagenes() ) );
        articuloManufacturado.unidadMedida( unidadMedidaDtoToUnidadMedida( dto.getUnidadMedida() ) );
        articuloManufacturado.categoria( categoriaShortDTOToCategoria( dto.getCategoria() ) );
        articuloManufacturado.descripcion( dto.getDescripcion() );
        articuloManufacturado.tiempoEstimadoMinutos( dto.getTiempoEstimadoMinutos() );
        articuloManufacturado.preparacion( dto.getPreparacion() );
        articuloManufacturado.articuloManufacturadoDetalles( articuloManufacturadoDetalleDtoSetToArticuloManufacturadoDetalleSet( dto.getArticuloManufacturadoDetalles() ) );
        articuloManufacturado.sucursales( sucursalShortDTOSetToSucursalSet( dto.getSucursales() ) );

        return articuloManufacturado.build();
    }

    @Override
    public List<ArticuloInsumoDto> toDtoListInsumo(List<ArticuloInsumo> articulosInsumos) {
        if ( articulosInsumos == null ) {
            return null;
        }

        List<ArticuloInsumoDto> list = new ArrayList<ArticuloInsumoDto>( articulosInsumos.size() );
        for ( ArticuloInsumo articuloInsumo : articulosInsumos ) {
            list.add( toDTO( articuloInsumo ) );
        }

        return list;
    }

    @Override
    public List<ArticuloManufacturadoDto> toDtoListManufacturado(List<ArticuloManufacturado> articulosManufacturados) {
        if ( articulosManufacturados == null ) {
            return null;
        }

        List<ArticuloManufacturadoDto> list = new ArrayList<ArticuloManufacturadoDto>( articulosManufacturados.size() );
        for ( ArticuloManufacturado articuloManufacturado : articulosManufacturados ) {
            list.add( toDTO( articuloManufacturado ) );
        }

        return list;
    }

    protected Set<ImagenDto> imagenArticuloSetToImagenDtoSet(Set<ImagenArticulo> set) {
        if ( set == null ) {
            return null;
        }

        Set<ImagenDto> set1 = new LinkedHashSet<ImagenDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ImagenArticulo imagenArticulo : set ) {
            set1.add( imagenArticuloMapper.toDTO( imagenArticulo ) );
        }

        return set1;
    }

    protected UnidadMedidaDto unidadMedidaToUnidadMedidaDto(UnidadMedida unidadMedida) {
        if ( unidadMedida == null ) {
            return null;
        }

        UnidadMedidaDto unidadMedidaDto = new UnidadMedidaDto();

        unidadMedidaDto.setId( unidadMedida.getId() );
        unidadMedidaDto.setEliminado( unidadMedida.isEliminado() );
        unidadMedidaDto.setFechaBaja( unidadMedida.getFechaBaja() );
        unidadMedidaDto.setDenominacion( unidadMedida.getDenominacion() );

        return unidadMedidaDto;
    }

    protected CategoriaShortDTO categoriaToCategoriaShortDTO(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaShortDTO categoriaShortDTO = new CategoriaShortDTO();

        categoriaShortDTO.setId( categoria.getId() );
        categoriaShortDTO.setEliminado( categoria.isEliminado() );
        categoriaShortDTO.setFechaBaja( categoria.getFechaBaja() );
        categoriaShortDTO.setDenominacion( categoria.getDenominacion() );

        return categoriaShortDTO;
    }

    protected Set<ImagenArticulo> imagenDtoSetToImagenArticuloSet(Set<ImagenDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<ImagenArticulo> set1 = new LinkedHashSet<ImagenArticulo>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ImagenDto imagenDto : set ) {
            set1.add( imagenArticuloMapper.toEntity( imagenDto ) );
        }

        return set1;
    }

    protected UnidadMedida unidadMedidaDtoToUnidadMedida(UnidadMedidaDto unidadMedidaDto) {
        if ( unidadMedidaDto == null ) {
            return null;
        }

        UnidadMedida.UnidadMedidaBuilder<?, ?> unidadMedida = UnidadMedida.builder();

        unidadMedida.id( unidadMedidaDto.getId() );
        unidadMedida.eliminado( unidadMedidaDto.isEliminado() );
        unidadMedida.fechaBaja( unidadMedidaDto.getFechaBaja() );
        unidadMedida.denominacion( unidadMedidaDto.getDenominacion() );

        return unidadMedida.build();
    }

    protected Categoria categoriaShortDTOToCategoria(CategoriaShortDTO categoriaShortDTO) {
        if ( categoriaShortDTO == null ) {
            return null;
        }

        Categoria.CategoriaBuilder<?, ?> categoria = Categoria.builder();

        categoria.id( categoriaShortDTO.getId() );
        categoria.eliminado( categoriaShortDTO.isEliminado() );
        categoria.fechaBaja( categoriaShortDTO.getFechaBaja() );
        categoria.denominacion( categoriaShortDTO.getDenominacion() );

        return categoria.build();
    }

    protected Set<StockInsumoSucursal> stockInsumoSucursalDTOSetToStockInsumoSucursalSet(Set<StockInsumoSucursalDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<StockInsumoSucursal> set1 = new LinkedHashSet<StockInsumoSucursal>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( StockInsumoSucursalDTO stockInsumoSucursalDTO : set ) {
            set1.add( stockInsumoSucursalMapper.toEntity( stockInsumoSucursalDTO ) );
        }

        return set1;
    }

    protected Set<StockInsumoSucursalDTO> stockInsumoSucursalSetToStockInsumoSucursalDTOSet(Set<StockInsumoSucursal> set) {
        if ( set == null ) {
            return null;
        }

        Set<StockInsumoSucursalDTO> set1 = new LinkedHashSet<StockInsumoSucursalDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( StockInsumoSucursal stockInsumoSucursal : set ) {
            set1.add( stockInsumoSucursalMapper.toDTO( stockInsumoSucursal ) );
        }

        return set1;
    }

    protected Set<ArticuloManufacturadoDetalleDto> articuloManufacturadoDetalleSetToArticuloManufacturadoDetalleDtoSet(Set<ArticuloManufacturadoDetalle> set) {
        if ( set == null ) {
            return null;
        }

        Set<ArticuloManufacturadoDetalleDto> set1 = new LinkedHashSet<ArticuloManufacturadoDetalleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ArticuloManufacturadoDetalle articuloManufacturadoDetalle : set ) {
            set1.add( articuloManufacturadoDetalleMapper.toDTO( articuloManufacturadoDetalle ) );
        }

        return set1;
    }

    protected SucursalShortDTO sucursalToSucursalShortDTO(Sucursal sucursal) {
        if ( sucursal == null ) {
            return null;
        }

        SucursalShortDTO sucursalShortDTO = new SucursalShortDTO();

        sucursalShortDTO.setId( sucursal.getId() );
        sucursalShortDTO.setEliminado( sucursal.isEliminado() );
        sucursalShortDTO.setFechaBaja( sucursal.getFechaBaja() );
        sucursalShortDTO.setNombre( sucursal.getNombre() );
        sucursalShortDTO.setEsCasaMatriz( sucursal.isEsCasaMatriz() );

        return sucursalShortDTO;
    }

    protected Set<SucursalShortDTO> sucursalSetToSucursalShortDTOSet(Set<Sucursal> set) {
        if ( set == null ) {
            return null;
        }

        Set<SucursalShortDTO> set1 = new LinkedHashSet<SucursalShortDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Sucursal sucursal : set ) {
            set1.add( sucursalToSucursalShortDTO( sucursal ) );
        }

        return set1;
    }

    protected Set<ArticuloManufacturadoDetalle> articuloManufacturadoDetalleDtoSetToArticuloManufacturadoDetalleSet(Set<ArticuloManufacturadoDetalleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<ArticuloManufacturadoDetalle> set1 = new LinkedHashSet<ArticuloManufacturadoDetalle>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ArticuloManufacturadoDetalleDto articuloManufacturadoDetalleDto : set ) {
            set1.add( articuloManufacturadoDetalleMapper.toEntity( articuloManufacturadoDetalleDto ) );
        }

        return set1;
    }

    protected Sucursal sucursalShortDTOToSucursal(SucursalShortDTO sucursalShortDTO) {
        if ( sucursalShortDTO == null ) {
            return null;
        }

        Sucursal.SucursalBuilder<?, ?> sucursal = Sucursal.builder();

        sucursal.id( sucursalShortDTO.getId() );
        sucursal.eliminado( sucursalShortDTO.isEliminado() );
        sucursal.fechaBaja( sucursalShortDTO.getFechaBaja() );
        sucursal.nombre( sucursalShortDTO.getNombre() );
        sucursal.esCasaMatriz( sucursalShortDTO.isEsCasaMatriz() );

        return sucursal.build();
    }

    protected Set<Sucursal> sucursalShortDTOSetToSucursalSet(Set<SucursalShortDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Sucursal> set1 = new LinkedHashSet<Sucursal>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( SucursalShortDTO sucursalShortDTO : set ) {
            set1.add( sucursalShortDTOToSucursal( sucursalShortDTO ) );
        }

        return set1;
    }
}
