package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.SucursalDto;

import java.util.List;

public interface Sucursalfacade extends BaseFacade<SucursalDto, Long> {
    SucursalDto createSucursal(SucursalDto dto);
    SucursalDto updateSucursal(Long id,SucursalDto dto);
    List<SucursalDto> getAllByUbicacion(Long idProvincia, Long idLocalidad);
    List<SucursalDto> getAllByEmpresa(Long idEmpresa);
}
