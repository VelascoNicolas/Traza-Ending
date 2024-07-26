package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado,Long> {
    String deleteCascade(Long id);
    List<ArticuloManufacturado> getBySucursal(Long id);
}
