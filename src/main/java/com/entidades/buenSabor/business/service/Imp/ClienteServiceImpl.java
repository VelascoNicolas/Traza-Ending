package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ClienteService;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.domain.entities.Cliente;
import com.entidades.buenSabor.domain.entities.ImagenCliente;
import com.entidades.buenSabor.repositories.ClienteRepository;
import com.entidades.buenSabor.repositories.ImagenClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImagenClienteRepository imagenRepo;

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> getTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getByUserName(String userName) {
        return clienteRepository.getCliente(userName);
    }

    @Override
    public Cliente update(String userName, Cliente cliente) {
        Cliente actual = clienteRepository.getCliente(userName);
        if (actual != null) {
            actual.setNombre(cliente.getNombre());
            actual.setApellido(cliente.getApellido());
            actual.setTelefono(cliente.getTelefono());
            actual.setDomicilios(cliente.getDomicilios());
            actual.setImagenCliente(cliente.getImagenCliente());
            return clienteRepository.save(actual);
        } else {
            return null;
        }
    }
}
