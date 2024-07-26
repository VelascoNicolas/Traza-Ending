package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.CategoriaFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.CategoriaMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.domain.dto.CategoriaClaseDTO;
import com.entidades.buenSabor.domain.dto.CategoriaHijoDto;
import com.entidades.buenSabor.domain.dto.CategoriaPadreDto;
import com.entidades.buenSabor.domain.dto.SucursalDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaFacadeImpl extends BaseFacadeImp<Categoria, CategoriaPadreDto,Long> implements CategoriaFacade {
    public CategoriaFacadeImpl(BaseService<Categoria, Long> baseService, BaseMapper<Categoria, CategoriaPadreDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public String deleteByID(Long id) {
        return categoriaService.deleteByID(id);
    }

    @Override
    public CategoriaHijoDto postCategoria(CategoriaPadreDto categoriaPadreDto) {
        Categoria request = categoriaMapper.toEntity(categoriaPadreDto);
        Categoria save = categoriaService.saveSucursal(request);
        return categoriaMapper.toShortDTO(request);
    }

    @Override
    public CategoriaHijoDto postCategoriaHijo(Long idCategoriaPadre, CategoriaHijoDto categoriaHijoDto) {
    //    categoriaHijoDto.setId(null);
        Categoria request = categoriaMapper.aEntidad(categoriaHijoDto);
        request.setId(null);
        Categoria save = categoriaService.asociarSubcategoria(idCategoriaPadre ,request);
        return categoriaMapper.toShortDTO(save);
    }

    @Override
    public List<CategoriaHijoDto> getCategoriasByPadre(Long idSucursal, Long idCategoriaPadre) {
        var entities = categoriaService.getCategoriasByPadre(idSucursal, idCategoriaPadre);
        return entities
                .stream()
                .map(categoriaMapper::toShortDTO)
                .toList();
    }

    @Override
    public List<CategoriaHijoDto> getCategoriasPadre(Long idSucursal) {
        var entities = categoriaService.getCategoriasPadre(idSucursal);
        return entities
                .stream()
                .map(categoriaMapper::toShortDTO)
                .toList();
    }

    @Override
    public List<CategoriaHijoDto> getAllCategoriasPadre() {
        var entities = categoriaService.getAllCategoriasPadre();
        return entities
                .stream()
                .map(categoriaMapper::toShortDTO)
                .toList();
    }

    @Override
    public CategoriaHijoDto putCategoria(Long id, CategoriaHijoDto categoriaHijoDto) {
        Categoria request = categoriaMapper.aEntidad(categoriaHijoDto);
        Categoria save = categoriaService.editado(id, request);
        return categoriaMapper.toShortDTO(save);
    }

    @Override
    public CategoriaHijoDto putPadre(Long id, CategoriaPadreDto categoriaPadreDto) {
        Categoria request = categoriaMapper.toEntity(categoriaPadreDto);
        Categoria save = categoriaService.editado(id, request);
        return categoriaMapper.toShortDTO(save);
    }

    @Override
    public List<CategoriaHijoDto> getAllHijasPorPadre(Long idPadre) {
        var entities = categoriaService.getAllHijasPorPadre(idPadre);
        return entities
                .stream()
                .map(categoriaMapper::toShortDTO)
                .toList();
    }
}
