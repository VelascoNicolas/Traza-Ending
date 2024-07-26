package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public Articulo getById(Long id) {
        return articuloRepository.findById(id).orElse(null);
    }
}
