package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.StockInsumoSucursalDTO;
import com.entidades.buenSabor.domain.dto.SucursalShortDTO;
import com.entidades.buenSabor.domain.entities.StockInsumoSucursal;
import com.entidades.buenSabor.domain.entities.Sucursal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-29T19:20:07-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class StockInsumoSucursalMapperImpl implements StockInsumoSucursalMapper {

    @Override
    public StockInsumoSucursalDTO toDTO(StockInsumoSucursal source) {
        if ( source == null ) {
            return null;
        }

        StockInsumoSucursalDTO stockInsumoSucursalDTO = new StockInsumoSucursalDTO();

        stockInsumoSucursalDTO.setId( source.getId() );
        stockInsumoSucursalDTO.setEliminado( source.isEliminado() );
        stockInsumoSucursalDTO.setFechaBaja( source.getFechaBaja() );
        stockInsumoSucursalDTO.setStockActual( source.getStockActual() );
        stockInsumoSucursalDTO.setStockMinimo( source.getStockMinimo() );
        stockInsumoSucursalDTO.setStockMaximo( source.getStockMaximo() );
        stockInsumoSucursalDTO.setSucursal( sucursalToSucursalShortDTO( source.getSucursal() ) );

        return stockInsumoSucursalDTO;
    }

    @Override
    public StockInsumoSucursal toEntity(StockInsumoSucursalDTO source) {
        if ( source == null ) {
            return null;
        }

        StockInsumoSucursal.StockInsumoSucursalBuilder<?, ?> stockInsumoSucursal = StockInsumoSucursal.builder();

        stockInsumoSucursal.id( source.getId() );
        stockInsumoSucursal.eliminado( source.isEliminado() );
        stockInsumoSucursal.fechaBaja( source.getFechaBaja() );
        stockInsumoSucursal.stockActual( source.getStockActual() );
        stockInsumoSucursal.stockMinimo( source.getStockMinimo() );
        stockInsumoSucursal.stockMaximo( source.getStockMaximo() );
        stockInsumoSucursal.sucursal( sucursalShortDTOToSucursal( source.getSucursal() ) );

        return stockInsumoSucursal.build();
    }

    @Override
    public List<StockInsumoSucursalDTO> toDTOsList(List<StockInsumoSucursal> source) {
        if ( source == null ) {
            return null;
        }

        List<StockInsumoSucursalDTO> list = new ArrayList<StockInsumoSucursalDTO>( source.size() );
        for ( StockInsumoSucursal stockInsumoSucursal : source ) {
            list.add( toDTO( stockInsumoSucursal ) );
        }

        return list;
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
}
