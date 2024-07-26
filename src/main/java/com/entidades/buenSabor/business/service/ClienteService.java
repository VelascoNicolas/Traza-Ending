package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.domain.entities.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ClienteService {
    Cliente save(Cliente cliente);
    List<Cliente> getTodos();
    Cliente getByUserName(String userName);
    Cliente update(String userName, Cliente cliente);
}
