package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Empleado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface EmpleadoService extends BaseService<Empleado, Long> {
    Empleado getByUserName(String userName);
    List<Empleado> getBySucursalId(Long id);

    List<Empleado> getInactivos();
}
