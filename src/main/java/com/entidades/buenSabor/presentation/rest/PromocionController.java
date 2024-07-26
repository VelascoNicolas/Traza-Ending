package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.dto.PromocionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/promocion")
@CrossOrigin("*")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok().body(promocionService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(promocionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> guardarPromocion(@RequestBody PromocionDto dto) {
        return ResponseEntity.ok().body(promocionService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPromocion(@RequestBody PromocionDto dto, @PathVariable Long id, @RequestParam LocalDate fechaActual) {
        return ResponseEntity.ok().body(promocionService.editarPromocion(dto, id, fechaActual));
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<List<?>> getPromocionesBySucursalId(@PathVariable Long id) {
        return ResponseEntity.ok().body(promocionService.getPromocionesBySucursalId(id));
    }
}
