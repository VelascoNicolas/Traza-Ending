package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.SucursalService;
import com.entidades.buenSabor.domain.entities.Domicilio;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.DomicilioRepository;
import com.entidades.buenSabor.repositories.EmpresaRepository;
import com.entidades.buenSabor.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SucursalServiceImpl extends BaseServiceImp<Sucursal,Long> implements SucursalService {
   @Autowired
   SucursalRepository sucursalRepository;
   @Autowired
   DomicilioRepository domicilioRepository;
   @Autowired
   EmpresaRepository empresaRepository;
    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImp.class);

    @Override
    @Transactional
    public Sucursal guardarSucursal(Sucursal sucursal) {
        var domicilio = sucursal.getDomicilio();
        domicilioRepository.save(domicilio);
        var empresa = empresaRepository.findById(sucursal.getEmpresa().getId());
        if(empresa.isEmpty()){
            throw new RuntimeException("No se puede guardar el empresa");
        }
        sucursal.setDomicilio(domicilio);

        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal actualizarSucursal(Long id,Sucursal sucursal) {
        var sucursalActualizar = sucursalRepository.getById(sucursal.getId());

        var domicilio = domicilioRepository.getById(sucursal.getDomicilio().getId());
        domicilioRepository.save(sucursal.getDomicilio());
        var empresa = empresaRepository.getById(sucursal.getEmpresa().getId());

        sucursal.setDomicilio(domicilio);
        sucursal.setEmpresa(empresa);
        return sucursalRepository.save(sucursal);
    }

    @Override
    public List<Sucursal> getAllByUbicacion(Long idProvincia, Long idLocalidad) {
        //return sucursalRepository.getAllByUbicacion(idProvincia, idLocalidad);
        List<Sucursal> sucursales = sucursalRepository.findAll();
        List<Sucursal> ubicacion = new ArrayList<>();
        for (Sucursal sucursal : sucursales) {
            if (sucursal.getDomicilio().getLocalidad().getId() == idLocalidad && sucursal.getDomicilio().getLocalidad().getProvincia().getId() == idProvincia) {
                ubicacion.add(sucursal);
            }
        }
        return ubicacion;
    }

    @Override
    public List<Sucursal> getAllByEmpresa(Long idEmpresa) {
        return sucursalRepository.getAllByEmpresa(idEmpresa);
    }


}
