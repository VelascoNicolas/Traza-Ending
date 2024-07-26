package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.DomicilioDto;
import com.entidades.buenSabor.domain.dto.EmpresaDto;
import com.entidades.buenSabor.domain.dto.EmpresaLargeDto;
import com.entidades.buenSabor.domain.dto.LocalidadDto;
import com.entidades.buenSabor.domain.dto.PaisDto;
import com.entidades.buenSabor.domain.dto.ProvinciaDto;
import com.entidades.buenSabor.domain.dto.SucursalDto;
import com.entidades.buenSabor.domain.entities.Domicilio;
import com.entidades.buenSabor.domain.entities.Empresa;
import com.entidades.buenSabor.domain.entities.Localidad;
import com.entidades.buenSabor.domain.entities.Pais;
import com.entidades.buenSabor.domain.entities.Provincia;
import com.entidades.buenSabor.domain.entities.Sucursal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-26T17:52:56-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class EmpresaMapperImpl implements EmpresaMapper {

    @Override
    public EmpresaDto toDTO(Empresa source) {
        if ( source == null ) {
            return null;
        }

        EmpresaDto empresaDto = new EmpresaDto();

        empresaDto.setId( source.getId() );
        empresaDto.setEliminado( source.isEliminado() );
        empresaDto.setFechaBaja( source.getFechaBaja() );
        empresaDto.setNombre( source.getNombre() );
        empresaDto.setRazonSocial( source.getRazonSocial() );
        empresaDto.setCuil( source.getCuil() );

        return empresaDto;
    }

    @Override
    public Empresa toEntity(EmpresaDto source) {
        if ( source == null ) {
            return null;
        }

        Empresa.EmpresaBuilder<?, ?> empresa = Empresa.builder();

        empresa.id( source.getId() );
        empresa.eliminado( source.isEliminado() );
        empresa.fechaBaja( source.getFechaBaja() );
        empresa.nombre( source.getNombre() );
        empresa.razonSocial( source.getRazonSocial() );
        empresa.cuil( source.getCuil() );

        return empresa.build();
    }

    @Override
    public List<EmpresaDto> toDTOsList(List<Empresa> source) {
        if ( source == null ) {
            return null;
        }

        List<EmpresaDto> list = new ArrayList<EmpresaDto>( source.size() );
        for ( Empresa empresa : source ) {
            list.add( toDTO( empresa ) );
        }

        return list;
    }

    @Override
    public EmpresaLargeDto toLargeDto(Empresa empresa) {
        if ( empresa == null ) {
            return null;
        }

        EmpresaLargeDto empresaLargeDto = new EmpresaLargeDto();

        empresaLargeDto.setId( empresa.getId() );
        empresaLargeDto.setEliminado( empresa.isEliminado() );
        empresaLargeDto.setFechaBaja( empresa.getFechaBaja() );
        empresaLargeDto.setNombre( empresa.getNombre() );
        empresaLargeDto.setRazonSocial( empresa.getRazonSocial() );
        empresaLargeDto.setCuil( empresa.getCuil() );
        empresaLargeDto.setSucursales( sucursalSetToSucursalDtoSet( empresa.getSucursales() ) );

        return empresaLargeDto;
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

    protected LocalidadDto localidadToLocalidadDto(Localidad localidad) {
        if ( localidad == null ) {
            return null;
        }

        LocalidadDto localidadDto = new LocalidadDto();

        localidadDto.setId( localidad.getId() );
        localidadDto.setEliminado( localidad.isEliminado() );
        localidadDto.setFechaBaja( localidad.getFechaBaja() );
        localidadDto.setNombre( localidad.getNombre() );
        localidadDto.setProvincia( provinciaToProvinciaDto( localidad.getProvincia() ) );

        return localidadDto;
    }

    protected DomicilioDto domicilioToDomicilioDto(Domicilio domicilio) {
        if ( domicilio == null ) {
            return null;
        }

        DomicilioDto domicilioDto = new DomicilioDto();

        domicilioDto.setId( domicilio.getId() );
        domicilioDto.setEliminado( domicilio.isEliminado() );
        domicilioDto.setFechaBaja( domicilio.getFechaBaja() );
        domicilioDto.setCalle( domicilio.getCalle() );
        domicilioDto.setNumero( domicilio.getNumero() );
        domicilioDto.setCp( domicilio.getCp() );
        domicilioDto.setPiso( domicilio.getPiso() );
        domicilioDto.setNroDpto( domicilio.getNroDpto() );
        domicilioDto.setLocalidad( localidadToLocalidadDto( domicilio.getLocalidad() ) );

        return domicilioDto;
    }

    protected SucursalDto sucursalToSucursalDto(Sucursal sucursal) {
        if ( sucursal == null ) {
            return null;
        }

        SucursalDto sucursalDto = new SucursalDto();

        sucursalDto.setId( sucursal.getId() );
        sucursalDto.setEliminado( sucursal.isEliminado() );
        sucursalDto.setFechaBaja( sucursal.getFechaBaja() );
        sucursalDto.setNombre( sucursal.getNombre() );
        sucursalDto.setEsCasaMatriz( sucursal.isEsCasaMatriz() );
        sucursalDto.setDomicilio( domicilioToDomicilioDto( sucursal.getDomicilio() ) );
        sucursalDto.setEmpresa( toDTO( sucursal.getEmpresa() ) );

        return sucursalDto;
    }

    protected Set<SucursalDto> sucursalSetToSucursalDtoSet(Set<Sucursal> set) {
        if ( set == null ) {
            return null;
        }

        Set<SucursalDto> set1 = new LinkedHashSet<SucursalDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Sucursal sucursal : set ) {
            set1.add( sucursalToSucursalDto( sucursal ) );
        }

        return set1;
    }
}
