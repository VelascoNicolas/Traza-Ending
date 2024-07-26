package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.DomicilioDto;
import com.entidades.buenSabor.domain.dto.StockInsumoSucursalDTO;
import com.entidades.buenSabor.domain.entities.StockInsumoSucursal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = SucursalMapper.class)
public interface StockInsumoSucursalMapper extends BaseMapper<StockInsumoSucursal, StockInsumoSucursalDTO>{
}
