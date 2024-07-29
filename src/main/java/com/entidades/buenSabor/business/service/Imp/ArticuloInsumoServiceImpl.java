package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloInsumoService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.repositories.*;
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
public class ArticuloInsumoServiceImpl extends BaseServiceImp<ArticuloInsumo,Long> implements ArticuloInsumoService {

    @Autowired
    private ArticuloManufacturadoDetalleRepository detalleRepository;

    @Autowired
    private ArticuloInsumoRepository articuloRepository;

    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ImagenArticuloRepository imagenRepo;

    @Autowired
    private StockInsumoSucursalRepository stockInsumoSucursalRepository;

    @Override
    public String deleteLogico(Long id) {
        if (detalleRepository.contiene(id) == false) {
            imagenRepo.deleteLogico(id);
            stockInsumoSucursalRepository.deleteLogico(id);
            deleteById(id);
            return "ARTICULO ELIMINADO CORRECTAMENTE";
        } else {
            return "ARTICULO NO SE PUEDE ELIMINAR PERTENECE A UN DETALLE";
        }
    }

    @Override
    public List<ArticuloInsumo> getArticuloInsumoBySucursal(Long idSucursal) {
        return articuloRepository.getArticuloInsumoBySucursal(idSucursal);
    }

    @Override
    public List<ArticuloInsumo> getElaborados(Long idSucursal) {
        return articuloRepository.getElaborados(idSucursal);
    }

    @Override
    public List<ArticuloInsumo> getNoElaborados(Long idSucursal) {
        return articuloRepository.getNoElaborados(idSucursal);
    }

    @Override
    public List<ArticuloInsumo> getBySucursalYCategoria(Long idSucursal, Long idCategoria) {
        return articuloRepository.getArticuloInsumoBySucursalAndCategoria(idSucursal, idCategoria);
    }
}
