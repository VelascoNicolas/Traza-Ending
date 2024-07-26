package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.UnidadMedidaFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.UnidadMedidaMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.UnidadMedidaService;
import com.entidades.buenSabor.domain.dto.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.UnidadMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadMedidaFacadeImpl extends BaseFacadeImp<UnidadMedida, UnidadMedidaDto, Long> implements UnidadMedidaFacade {

    public UnidadMedidaFacadeImpl(BaseService<UnidadMedida, Long> baseService, BaseMapper<UnidadMedida, UnidadMedidaDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private UnidadMedidaMapper unidadMedidaMapper;

    @Autowired
    private UnidadMedidaService unidadMedidaService;


    @Override
    public UnidadMedidaDto createUnidadMedida(UnidadMedidaDto dto) {
        UnidadMedida request = unidadMedidaMapper.toEntity(dto);
        UnidadMedida save = unidadMedidaService.create(request);
        return unidadMedidaMapper.toDTO(save);
    }

    @Override
    public UnidadMedidaDto updateUnidadMedida(Long id, UnidadMedidaDto dto) {
        UnidadMedida request = unidadMedidaService.getById(id);
        request.setDenominacion(dto.getDenominacion());
        UnidadMedida save = unidadMedidaService.create(request);
        return unidadMedidaMapper.toDTO(save);
    }
}
