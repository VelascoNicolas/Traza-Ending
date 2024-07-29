package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.ArticuloInsumoFacade;
import com.entidades.buenSabor.domain.dto.ArticuloInsumoDto;
import com.entidades.buenSabor.presentation.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/articuloInsumo")
@CrossOrigin(origins="*")
public class ArticuloInsumoController {
    private static final Logger logger = LoggerFactory.getLogger(BaseControllerImp.class);
    
    @Autowired
    private ArticuloInsumoFacade articuloInsumoFacade;

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloInsumoDto> getById(@PathVariable Long id){
        logger.info("INICIO GET BY Long {}",id);
        return ResponseEntity.ok(articuloInsumoFacade.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ArticuloInsumoDto>> getAll() {
        logger.info("INICIO GET ALL");
        return ResponseEntity.ok(articuloInsumoFacade.getAll());
    }

    @PostMapping()
    public ResponseEntity<ArticuloInsumoDto> create(@RequestBody ArticuloInsumoDto entity){
        logger.info("INICIO CREATE {}",entity.getClass());
        return ResponseEntity.ok(articuloInsumoFacade.createNew(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticuloInsumoDto> edit(@RequestBody ArticuloInsumoDto entity, @PathVariable Long id){
        logger.info("INICIO EDIT {}",entity.getClass());
        return ResponseEntity.ok(articuloInsumoFacade.update(entity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        logger.info("INICIO DELETE BY Long");
        return ResponseEntity.ok(articuloInsumoFacade.deleteById(id));
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<?> getArticulosInsumosBySucursal(@PathVariable Long id) {
        logger.info("INICIO GET ARTICULOS INSUMOS BY Sucursal");
        return ResponseEntity.ok(articuloInsumoFacade.getArticuloInsumoBySucursal(id));
    }


    @GetMapping("/elaborados")
    public ResponseEntity<List<?>> getElaborados() {
        logger.info("INICIO GET ELABORADOS");
        return ResponseEntity.ok(articuloInsumoFacade.getElaborados());
    }

    @GetMapping("/noElaborados/sucursal/{idSucursal}")
    public ResponseEntity<List<?>> getNoElaborados(@PathVariable Long idSucursal) {
        logger.info("INICIO GET NO ELABORADOS");
        return ResponseEntity.ok(articuloInsumoFacade.getNoElaborados(idSucursal));
    }

    @GetMapping("/{idSucursal}/{idCategoria}")
    public ResponseEntity<?> getArticulosInsumosBySucursalAndCategoria(@PathVariable Long idSucursal, @PathVariable Long idCategoria) {
        logger.info("INICIO GET ARTICULOS INSUMOS BY Sucursal And Categoria");
        return ResponseEntity.ok(articuloInsumoFacade.getArticuloInsumoBySucursalAndCategoria(idSucursal, idCategoria));
    }
}
