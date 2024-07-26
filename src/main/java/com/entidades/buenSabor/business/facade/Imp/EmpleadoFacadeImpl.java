package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.EmpleadoFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.EmpleadoMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.EmpleadoService;
import com.entidades.buenSabor.domain.dto.EmpleadoDTO;
import com.entidades.buenSabor.domain.entities.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class EmpleadoFacadeImpl extends BaseFacadeImp<Empleado, EmpleadoDTO, Long> implements EmpleadoFacade {
    public EmpleadoFacadeImpl(BaseService<Empleado, Long> baseService, BaseMapper<Empleado, EmpleadoDTO> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    @Override
    public List<EmpleadoDTO> getAllEmpleados() {
        return empleadoMapper.toDTOsList(empleadoService.getAll());
    }

    @Override
    public EmpleadoDTO getByUserName(String userName) {
        Empleado empleado = empleadoService.getByUserName(userName);
        EmpleadoDTO dto = empleadoMapper.toDTO(empleado);
        return dto;
    }

    @Override
    public List<EmpleadoDTO> getBySucursalId(Long id) {
        List<Empleado> empleados = empleadoService.getBySucursalId(id);
        List<EmpleadoDTO> dtos = empleadoMapper.toDTOsList(empleados);
        return dtos;
    }

    @Override
    public List<EmpleadoDTO> getInactivos() {
        List<Empleado> empleados = empleadoService.getInactivos();
        List<EmpleadoDTO> dtos = empleadoMapper.toDTOsList(empleados);
        return dtos;
    }
}
