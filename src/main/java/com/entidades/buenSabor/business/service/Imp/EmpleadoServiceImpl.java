package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.business.service.EmpleadoService;
import com.entidades.buenSabor.domain.entities.Empleado;
import com.entidades.buenSabor.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl extends BaseServiceImp<Empleado, Long> implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;


    @Override
    public Empleado getByUserName(String userName) {
        return empleadoRepository.getByUserName(userName);
    }

    @Override
    public List<Empleado> getBySucursalId(Long id) {
        return empleadoRepository.getBySucursalId(id);
    }

    @Override
    public List<Empleado> getInactivos() {
        return empleadoRepository.getInactivos();
    }
}
