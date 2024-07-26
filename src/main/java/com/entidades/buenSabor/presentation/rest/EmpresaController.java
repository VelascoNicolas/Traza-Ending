package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.EmpresaFacadeImpl;

import com.entidades.buenSabor.domain.dto.EmpresaDto;

import com.entidades.buenSabor.domain.entities.Empresa;

import com.entidades.buenSabor.presentation.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
@CrossOrigin("*")
public class EmpresaController extends BaseControllerImp<Empresa, EmpresaDto, Long, EmpresaFacadeImpl> {
    public EmpresaController(EmpresaFacadeImpl facade) {
        super(facade);
    }

    @Override
    @PostMapping
    public ResponseEntity<EmpresaDto> create(@RequestBody EmpresaDto entity) {
        return super.create(entity);
    }

    @Override
    @PutMapping("/{id}")
       public ResponseEntity<EmpresaDto> edit (EmpresaDto entity, Long id){
        return super.edit(entity,id);
    }
}
