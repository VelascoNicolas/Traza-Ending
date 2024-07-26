package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Sucursal;

import java.util.List;

public interface SucursalService  extends BaseService<Sucursal, Long> {
    Sucursal guardarSucursal(Sucursal sucursal);
    Sucursal actualizarSucursal(Long id,Sucursal sucursal);
    List<Sucursal> getAllByUbicacion(Long idProvincia, Long idLocalidad);
    List<Sucursal> getAllByEmpresa(Long idEmpresa);
}

