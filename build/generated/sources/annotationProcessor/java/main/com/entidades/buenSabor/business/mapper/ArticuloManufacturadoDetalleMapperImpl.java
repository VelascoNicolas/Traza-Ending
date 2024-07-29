package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.CategoriaShortDTO;
import com.entidades.buenSabor.domain.dto.ImagenDto;
import com.entidades.buenSabor.domain.dto.StockInsumoSucursalDTO;
import com.entidades.buenSabor.domain.dto.SucursalShortDTO;
import com.entidades.buenSabor.domain.dto.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
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
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-29T19:20:08-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class ArticuloManufacturadoDetalleMapperImpl implements ArticuloManufacturadoDetalleMapper {

    @Override
    public ArticuloManufacturadoDetalleDto toDTO(ArticuloManufacturadoDetalle source) {
        if ( source == null ) {
            return null;
        }

        ArticuloManufacturadoDetalleDto articuloManufacturadoDetalleDto = new ArticuloManufacturadoDetalleDto();

        articuloManufacturadoDetalleDto.setId( source.getId() );
        articuloManufacturadoDetalleDto.setEliminado( source.isEliminado() );
        articuloManufacturadoDetalleDto.setFechaBaja( source.getFechaBaja() );
        articuloManufacturadoDetalleDto.setCantidad( source.getCantidad() );
        articuloManufacturadoDetalleDto.setArticuloInsumo( articuloInsumoToArticuloInsumoDto( source.getArticuloInsumo() ) );

        return articuloManufacturadoDetalleDto;
    }

    @Override
    public ArticuloManufacturadoDetalle toEntity(ArticuloManufacturadoDetalleDto source) {
        if ( source == null ) {
            return null;
        }

        ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleBuilder<?, ?> articuloManufacturadoDetalle = ArticuloManufacturadoDetalle.builder();

        articuloManufacturadoDetalle.id( source.getId() );
        articuloManufacturadoDetalle.eliminado( source.isEliminado() );
        articuloManufacturadoDetalle.fechaBaja( source.getFechaBaja() );
        articuloManufacturadoDetalle.cantidad( source.getCantidad() );
        articuloManufacturadoDetalle.articuloInsumo( articuloInsumoDtoToArticuloInsumo( source.getArticuloInsumo() ) );

        return articuloManufacturadoDetalle.build();
    }

    @Override
    public List<ArticuloManufacturadoDetalleDto> toDTOsList(List<ArticuloManufacturadoDetalle> source) {
        if ( source == null ) {
            return null;
        }

        List<ArticuloManufacturadoDetalleDto> list = new ArrayList<ArticuloManufacturadoDetalleDto>( source.size() );
        for ( ArticuloManufacturadoDetalle articuloManufacturadoDetalle : source ) {
            list.add( toDTO( articuloManufacturadoDetalle ) );
        }

        return list;
    }

    protected ImagenDto imagenArticuloToImagenDto(ImagenArticulo imagenArticulo) {
        if ( imagenArticulo == null ) {
            return null;
        }

        ImagenDto imagenDto = new ImagenDto();

        imagenDto.setId( imagenArticulo.getId() );
        imagenDto.setEliminado( imagenArticulo.isEliminado() );
        imagenDto.setFechaBaja( imagenArticulo.getFechaBaja() );
        imagenDto.setUrl( imagenArticulo.getUrl() );

        return imagenDto;
    }

    protected Set<ImagenDto> imagenArticuloSetToImagenDtoSet(Set<ImagenArticulo> set) {
        if ( set == null ) {
            return null;
        }

        Set<ImagenDto> set1 = new LinkedHashSet<ImagenDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ImagenArticulo imagenArticulo : set ) {
            set1.add( imagenArticuloToImagenDto( imagenArticulo ) );
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

    protected StockInsumoSucursalDTO stockInsumoSucursalToStockInsumoSucursalDTO(StockInsumoSucursal stockInsumoSucursal) {
        if ( stockInsumoSucursal == null ) {
            return null;
        }

        StockInsumoSucursalDTO stockInsumoSucursalDTO = new StockInsumoSucursalDTO();

        stockInsumoSucursalDTO.setId( stockInsumoSucursal.getId() );
        stockInsumoSucursalDTO.setEliminado( stockInsumoSucursal.isEliminado() );
        stockInsumoSucursalDTO.setFechaBaja( stockInsumoSucursal.getFechaBaja() );
        stockInsumoSucursalDTO.setStockActual( stockInsumoSucursal.getStockActual() );
        stockInsumoSucursalDTO.setStockMinimo( stockInsumoSucursal.getStockMinimo() );
        stockInsumoSucursalDTO.setStockMaximo( stockInsumoSucursal.getStockMaximo() );
        stockInsumoSucursalDTO.setSucursal( sucursalToSucursalShortDTO( stockInsumoSucursal.getSucursal() ) );

        return stockInsumoSucursalDTO;
    }

    protected Set<StockInsumoSucursalDTO> stockInsumoSucursalSetToStockInsumoSucursalDTOSet(Set<StockInsumoSucursal> set) {
        if ( set == null ) {
            return null;
        }

        Set<StockInsumoSucursalDTO> set1 = new LinkedHashSet<StockInsumoSucursalDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( StockInsumoSucursal stockInsumoSucursal : set ) {
            set1.add( stockInsumoSucursalToStockInsumoSucursalDTO( stockInsumoSucursal ) );
        }

        return set1;
    }

    protected ArticuloInsumoDto articuloInsumoToArticuloInsumoDto(ArticuloInsumo articuloInsumo) {
        if ( articuloInsumo == null ) {
            return null;
        }

        ArticuloInsumoDto articuloInsumoDto = new ArticuloInsumoDto();

        articuloInsumoDto.setId( articuloInsumo.getId() );
        articuloInsumoDto.setEliminado( articuloInsumo.isEliminado() );
        articuloInsumoDto.setFechaBaja( articuloInsumo.getFechaBaja() );
        articuloInsumoDto.setDenominacion( articuloInsumo.getDenominacion() );
        articuloInsumoDto.setPrecioVenta( articuloInsumo.getPrecioVenta() );
        articuloInsumoDto.setImagenes( imagenArticuloSetToImagenDtoSet( articuloInsumo.getImagenes() ) );
        articuloInsumoDto.setUnidadMedida( unidadMedidaToUnidadMedidaDto( articuloInsumo.getUnidadMedida() ) );
        articuloInsumoDto.setCategoria( categoriaToCategoriaShortDTO( articuloInsumo.getCategoria() ) );
        articuloInsumoDto.setPrecioCompra( articuloInsumo.getPrecioCompra() );
        articuloInsumoDto.setEsParaElaborar( articuloInsumo.getEsParaElaborar() );
        articuloInsumoDto.setStocksInsumo( stockInsumoSucursalSetToStockInsumoSucursalDTOSet( articuloInsumo.getStocksInsumo() ) );

        return articuloInsumoDto;
    }

    protected ImagenArticulo imagenDtoToImagenArticulo(ImagenDto imagenDto) {
        if ( imagenDto == null ) {
            return null;
        }

        ImagenArticulo.ImagenArticuloBuilder<?, ?> imagenArticulo = ImagenArticulo.builder();

        imagenArticulo.id( imagenDto.getId() );
        imagenArticulo.eliminado( imagenDto.isEliminado() );
        imagenArticulo.fechaBaja( imagenDto.getFechaBaja() );
        imagenArticulo.url( imagenDto.getUrl() );

        return imagenArticulo.build();
    }

    protected Set<ImagenArticulo> imagenDtoSetToImagenArticuloSet(Set<ImagenDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<ImagenArticulo> set1 = new LinkedHashSet<ImagenArticulo>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ImagenDto imagenDto : set ) {
            set1.add( imagenDtoToImagenArticulo( imagenDto ) );
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

    protected StockInsumoSucursal stockInsumoSucursalDTOToStockInsumoSucursal(StockInsumoSucursalDTO stockInsumoSucursalDTO) {
        if ( stockInsumoSucursalDTO == null ) {
            return null;
        }

        StockInsumoSucursal.StockInsumoSucursalBuilder<?, ?> stockInsumoSucursal = StockInsumoSucursal.builder();

        stockInsumoSucursal.id( stockInsumoSucursalDTO.getId() );
        stockInsumoSucursal.eliminado( stockInsumoSucursalDTO.isEliminado() );
        stockInsumoSucursal.fechaBaja( stockInsumoSucursalDTO.getFechaBaja() );
        stockInsumoSucursal.stockActual( stockInsumoSucursalDTO.getStockActual() );
        stockInsumoSucursal.stockMinimo( stockInsumoSucursalDTO.getStockMinimo() );
        stockInsumoSucursal.stockMaximo( stockInsumoSucursalDTO.getStockMaximo() );
        stockInsumoSucursal.sucursal( sucursalShortDTOToSucursal( stockInsumoSucursalDTO.getSucursal() ) );

        return stockInsumoSucursal.build();
    }

    protected Set<StockInsumoSucursal> stockInsumoSucursalDTOSetToStockInsumoSucursalSet(Set<StockInsumoSucursalDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<StockInsumoSucursal> set1 = new LinkedHashSet<StockInsumoSucursal>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( StockInsumoSucursalDTO stockInsumoSucursalDTO : set ) {
            set1.add( stockInsumoSucursalDTOToStockInsumoSucursal( stockInsumoSucursalDTO ) );
        }

        return set1;
    }

    protected ArticuloInsumo articuloInsumoDtoToArticuloInsumo(ArticuloInsumoDto articuloInsumoDto) {
        if ( articuloInsumoDto == null ) {
            return null;
        }

        ArticuloInsumo.ArticuloInsumoBuilder<?, ?> articuloInsumo = ArticuloInsumo.builder();

        articuloInsumo.id( articuloInsumoDto.getId() );
        articuloInsumo.eliminado( articuloInsumoDto.isEliminado() );
        articuloInsumo.fechaBaja( articuloInsumoDto.getFechaBaja() );
        articuloInsumo.denominacion( articuloInsumoDto.getDenominacion() );
        articuloInsumo.precioVenta( articuloInsumoDto.getPrecioVenta() );
        articuloInsumo.imagenes( imagenDtoSetToImagenArticuloSet( articuloInsumoDto.getImagenes() ) );
        articuloInsumo.unidadMedida( unidadMedidaDtoToUnidadMedida( articuloInsumoDto.getUnidadMedida() ) );
        articuloInsumo.categoria( categoriaShortDTOToCategoria( articuloInsumoDto.getCategoria() ) );
        articuloInsumo.precioCompra( articuloInsumoDto.getPrecioCompra() );
        articuloInsumo.esParaElaborar( articuloInsumoDto.getEsParaElaborar() );
        articuloInsumo.stocksInsumo( stockInsumoSucursalDTOSetToStockInsumoSucursalSet( articuloInsumoDto.getStocksInsumo() ) );

        return articuloInsumo.build();
    }
}
