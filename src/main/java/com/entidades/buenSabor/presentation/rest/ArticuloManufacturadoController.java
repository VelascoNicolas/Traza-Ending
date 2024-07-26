package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.ArticuloManufacturadoFacade;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDto;
import com.entidades.buenSabor.presentation.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articuloManufacturado")
@CrossOrigin(origins="*")
public class ArticuloManufacturadoController  {
    private static final Logger logger = LoggerFactory.getLogger(BaseControllerImp.class);

    @Autowired
    private ArticuloManufacturadoFacade articuloManufacturadoFacade;

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloManufacturadoDto> getById(@PathVariable Long id){
        logger.info("INICIO GET BY Long {}",id);
        return ResponseEntity.ok(articuloManufacturadoFacade.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ArticuloManufacturadoDto>> getAll() {
        logger.info("INICIO GET ALL");
        return ResponseEntity.ok(articuloManufacturadoFacade.getAll());
    }

    @PostMapping()
    public ResponseEntity<ArticuloManufacturadoDto> create(@RequestBody ArticuloManufacturadoDto entity){
        logger.info("INICIO CREATE {}",entity.getClass());
        return ResponseEntity.ok(articuloManufacturadoFacade.createNew(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticuloManufacturadoDto> edit(@RequestBody ArticuloManufacturadoDto entity, @PathVariable Long id){
        logger.info("INICIO EDIT {}",entity.getClass());
        return ResponseEntity.ok(articuloManufacturadoFacade.update(entity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        logger.info("INICIO DELETE BY Long");
        return ResponseEntity.ok(articuloManufacturadoFacade.deleteById(id));
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<?> getBySucursal(@PathVariable Long id) {
        logger.info("INICIO GET BY Long {}", id);
        return ResponseEntity.ok(articuloManufacturadoFacade.getBySucursal(id));
    }
}
