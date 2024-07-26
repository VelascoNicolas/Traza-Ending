package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.EmpleadoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface EmpleadoFacade extends BaseFacade<EmpleadoDTO, Long> {

    List<EmpleadoDTO> getAllEmpleados();
    EmpleadoDTO getByUserName(String userName);
    List<EmpleadoDTO> getBySucursalId(Long id);
    List<EmpleadoDTO> getInactivos();
}
