package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.ClienteService;
import com.entidades.buenSabor.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> getTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getTodos());
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getByUserName(@PathVariable String userName) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getByUserName(userName));
    }

    @PostMapping
    public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(cliente));
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> actualizarCliente(@PathVariable String userName, @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.update(userName, cliente));
    }
}