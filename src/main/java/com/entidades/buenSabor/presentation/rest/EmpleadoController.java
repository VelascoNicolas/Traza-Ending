package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.EmpleadoFacade;
import com.entidades.buenSabor.business.service.EmpleadoService;
import com.entidades.buenSabor.domain.dto.EmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService service;

    @Autowired
    private EmpleadoFacade empleadoFacade;

    @GetMapping
    public ResponseEntity<?> getEmpleados() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(empleadoFacade.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/email/{userName}")
    public ResponseEntity<?> getEmpleadoByUserName(@PathVariable String userName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(empleadoFacade.getByUserName(userName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<?> createEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(empleadoFacade.createNew(empleadoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<?> getEmpleadoBySucursalId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(empleadoFacade.getBySucursalId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpleado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(empleadoFacade.update(empleadoDTO, id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/inactivos")
    public ResponseEntity<?> getInactivos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(empleadoFacade.getInactivos());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
