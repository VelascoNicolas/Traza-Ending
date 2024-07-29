package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.domain.dto.ArticuloInsumoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public interface ArticuloInsumoFacade  {
    public ArticuloInsumoDto createNew(ArticuloInsumoDto request);
    public ArticuloInsumoDto getById(Long id);
    public List<ArticuloInsumoDto> getAll();
    public String deleteById(Long id);
    public ArticuloInsumoDto update(ArticuloInsumoDto request, Long id);
    public List<ArticuloInsumoDto> getArticuloInsumoBySucursal(Long idSucursal);
    public List<ArticuloInsumoDto> getElaborados();
    public List<ArticuloInsumoDto> getNoElaborados(Long idSucursal);
    public List<ArticuloInsumoDto> getArticuloInsumoBySucursalAndCategoria(Long idSucursal, Long idCategoria);
}
