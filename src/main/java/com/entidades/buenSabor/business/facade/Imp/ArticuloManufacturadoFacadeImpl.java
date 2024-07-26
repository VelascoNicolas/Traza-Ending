package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.ArticuloManufacturadoFacade;
import com.entidades.buenSabor.business.mapper.ArticuloMapper;
import com.entidades.buenSabor.business.service.ArticuloManufacturadoService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.SucursalShortDTO;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ArticuloManufacturadoFacadeImpl implements ArticuloManufacturadoFacade {
    @Autowired
    private ArticuloManufacturadoService articuloManufacturadoService;
    @Autowired
    private ArticuloMapper articuloMapper;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public ArticuloManufacturadoDto createNew(ArticuloManufacturadoDto request) {
        ArticuloManufacturado articuloManufacturado = articuloMapper.toEntity(request);
        ArticuloManufacturado savedEntity = articuloManufacturadoService.create(articuloManufacturado);
        for (SucursalShortDTO s: request.getSucursales()) {
            Sucursal suc = sucursalRepository.getById(s.getId());
            suc.getManufacturados().add(savedEntity);
            savedEntity.getSucursales().add(suc);
            sucursalRepository.save(suc);
            articuloManufacturadoService.update(savedEntity, savedEntity.getId());
        }
        return (ArticuloManufacturadoDto) articuloMapper.toDTO(savedEntity);
    }

    @Override
    public ArticuloManufacturadoDto getById(Long id) {
        ArticuloManufacturado articuloManufacturado = articuloManufacturadoService.getById(id);
        ArticuloManufacturadoDto articuloDTO = articuloMapper.toDTO(articuloManufacturado);
        return articuloDTO;
    }

    @Override
    public List<ArticuloManufacturadoDto> getAll() {
        List<ArticuloManufacturado> articulosManufacturados = articuloManufacturadoService.getAll();
        return articuloMapper.toDtoListManufacturado(articulosManufacturados);
    }

    @Override
    public String deleteById(Long id) {
        return articuloManufacturadoService.deleteCascade(id);
    }


    @Override
    public ArticuloManufacturadoDto update(ArticuloManufacturadoDto request, Long id) {
        ArticuloManufacturado articuloManufacturado = articuloMapper.toEntity(request);
        // Asegúrate de configurar el ID correctamente
        ArticuloManufacturado updatedEntity = articuloManufacturadoService.update(articuloManufacturado,id);
        return (ArticuloManufacturadoDto) articuloMapper.toDTO(updatedEntity);
    }

    @Override
    public List<ArticuloManufacturadoDto> getBySucursal(Long id) {
        List<ArticuloManufacturado> articulosManufacturados = articuloManufacturadoService.getBySucursal(id);
        return articuloMapper.toDtoListManufacturado(articulosManufacturados);
    }
}
