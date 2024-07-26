package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.ImagenArticuloFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.ImagenDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import org.springframework.stereotype.Service;

@Service
public class ImagenArticuloFacadeImpl extends BaseFacadeImp<ImagenArticulo, ImagenDto,Long> implements ImagenArticuloFacade {
    public ImagenArticuloFacadeImpl(BaseService<ImagenArticulo, Long> baseService, BaseMapper<ImagenArticulo, ImagenDto> baseMapper) {
        super(baseService, baseMapper);
    }
}