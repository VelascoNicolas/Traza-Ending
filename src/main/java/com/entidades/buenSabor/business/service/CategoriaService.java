package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Categoria;

import java.util.List;

public interface CategoriaService extends BaseService<Categoria,Long> {
    String deleteByID(Long id);
    List<Categoria> getCategoriasPadre(Long idSucursal);
    Categoria asociarSubcategoria(Long idCategoriaPadre, Categoria categoria);
    List<Categoria> getCategoriasByPadre(Long idSucursal, Long idCategoriaPadre);
    Categoria saveSucursal(Categoria categoria);
    List<Categoria> getAllCategoriasPadre();
    Categoria editado(Long id, Categoria categoria);
    List<Categoria> getAllHijasPorPadre(Long idPadre);
}
