package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ArticuloInsumoService extends BaseService<ArticuloInsumo,Long> {
    String deleteLogico(Long id);
    List<ArticuloInsumo> getArticuloInsumoBySucursal(Long idSucursal);
    List<ArticuloInsumo> getElaborados(Long idSucursal);
    List<ArticuloInsumo> getNoElaborados(Long idSucursal);
    List<ArticuloInsumo> getBySucursalYCategoria(Long idSucursal, Long idCategoria);
}
