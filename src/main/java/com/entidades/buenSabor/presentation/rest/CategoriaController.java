package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.CategoriaFacade;
import com.entidades.buenSabor.domain.dto.CategoriaHijoDto;
import com.entidades.buenSabor.domain.dto.CategoriaPadreDto;
import com.entidades.buenSabor.presentation.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins="*")
public class CategoriaController {
    private static final Logger logger = LoggerFactory.getLogger(BaseControllerImp.class);

    @Autowired
    private CategoriaFacade categoriaFacade;

    //POST PADRE
    @PostMapping("/padre")
    public ResponseEntity<?> createCategoria(@RequestBody CategoriaPadreDto categoriaPadreDto) {
        logger.info("INICIO CREATE {}", categoriaPadreDto.getClass());
        return ResponseEntity.ok(categoriaFacade.postCategoria(categoriaPadreDto));
    }

    //POST HIJO
    @PostMapping("/hijo/{idCategoriaPadre}")
    public ResponseEntity<?> createCategoriaHijo(@PathVariable Long idCategoriaPadre, @RequestBody CategoriaHijoDto categoriaHijoDto) {
        logger.info("INICIO CREATE {}", categoriaHijoDto.getClass());
        return ResponseEntity.ok(categoriaFacade.postCategoriaHijo(idCategoriaPadre, categoriaHijoDto));
    }

    //DELETE CATEGORIA
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        logger.info("INICIO DELETE BY Long");
        return ResponseEntity.ok(categoriaFacade.deleteByID(id));
    }

    //GET CATEGORIAS HIJO
    @GetMapping("/{idSucursal}/hijo/{idPadre}")
    public ResponseEntity<List<?>> getCategoriasByPadre(@PathVariable Long idSucursal, @PathVariable Long idPadre) {
        logger.info("INICIO GET CATEGORIAS BY PADRE");
        return ResponseEntity.ok(categoriaFacade.getCategoriasByPadre(idSucursal, idPadre));
    }

    //GET CATEGORIAS PADRE
    @GetMapping("/padres/{idSucursal}")
    public ResponseEntity<List<?>> getCategoriasPadre(@PathVariable Long idSucursal) {
        logger.info("INICIO GET CATEGORIAS PADRE");
        return ResponseEntity.ok(categoriaFacade.getCategoriasPadre(idSucursal));
    }

    //GET ALL CATEGORIAS PADRE
    @GetMapping("/padres")
    public ResponseEntity<List<?>> getAllCategoriasPadre() {
        logger.info("INICIO GET ALL CATEGORIAS PADRE");
        return ResponseEntity.ok(categoriaFacade.getAllCategoriasPadre());
    }

    //GET ALL HIJAS POR PADRE
    @GetMapping("/hijas/{idPadre}")
    public ResponseEntity<List<?>> getAllHijasPorPadre(@PathVariable Long idPadre) {
        logger.info("INICIO GET ALL HIJAS POR PADRE");
        return ResponseEntity.ok(categoriaFacade.getAllHijasPorPadre(idPadre));
    }

    //PUT PADRE
    @PutMapping("/padre/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable Long id, @RequestBody CategoriaPadreDto categoriaPadreDto) {
        logger.info("INICIO UPDATE CATEGORIA PADRE");
        return ResponseEntity.ok(categoriaFacade.putPadre(id, categoriaPadreDto));
    }

    //PUT HIJO
    @PutMapping("hijo/{id}")
    public ResponseEntity<?> updateCategoriaHijo(@PathVariable Long id, @RequestBody CategoriaHijoDto categoriaHijoDto) {
        logger.info("INICIO UPDATE CATEGORIA HIJO");
        return ResponseEntity.ok(categoriaFacade.putCategoria(id, categoriaHijoDto));
    }
}
