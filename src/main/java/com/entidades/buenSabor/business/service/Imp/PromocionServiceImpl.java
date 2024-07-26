package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.dto.ImagenDto;
import com.entidades.buenSabor.domain.dto.PromocionDetalleDto;
import com.entidades.buenSabor.domain.dto.PromocionDto;
import com.entidades.buenSabor.domain.dto.SucursalDto;
import com.entidades.buenSabor.domain.entities.ImagenPromocion;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;

@Service
public class PromocionServiceImpl extends BaseServiceImp<Promocion,Long> implements PromocionService {

    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary

    @Autowired
    private ImagenPromocionRepository imagenRepo;

    @Autowired
    private PromocionRepository promocionRepository;

    @Autowired
    private PromocionDetalleRepository promocionDetalleRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private SucursalRepository sucursalRepository;


    // Método para obtener todas las imágenes almacenadas
    @Override
    //pedimos el id para retornar solo las imagenes de un articulo
    public ResponseEntity<List<Map<String, Object>>> getAllImagesByPromocionId(Long id) {
        try {
            // Consultar todas las imágenes desde la base de datos
            List<ImagenPromocion> images = baseRepository.getById(id).getImagenes().stream().toList();
            List<Map<String, Object>> imageList = new ArrayList<>();

            // Convertir cada imagen en un mapa de atributos para devolver como JSON
            for (ImagenPromocion image : images) {
                Map<String, Object> imageMap = new HashMap<>();
                imageMap.put("id", image.getId());
                imageMap.put("url", image.getUrl());
                imageList.add(imageMap);
            }

            // Devolver la lista de imágenes como ResponseEntity con estado OK (200)
            return ResponseEntity.ok(imageList);
        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error interno del servidor (500) si ocurre alguna excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para subir imágenes a Cloudinary y guardar los detalles en la base de datos
    @Override
    //Pedimos el id de articulo para saber a que articulo asignar las imagenes
    public ResponseEntity<String> uploadImages(MultipartFile[] files, Long idPromocion) {
        List<String> urls = new ArrayList<>();
        var promocion = baseRepository.getById(idPromocion);
        //por medio de un condicional limitamos la carga de imagenes a un maximo de 3 por aticulo
        //en caso de tratar de excer ese limite arroja un codigo 413 con el mensaje La cantidad maxima de imagenes es 3
        if(promocion.getImagenes().size() >= 3)
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("La cantidad maxima de imagenes es 3");
        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío
                if (file.isEmpty()) {
                    return ResponseEntity.badRequest().build();
                }

                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenPromocion image = new ImagenPromocion();
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL

                // Verificar si la URL de la imagen es nula (indicativo de fallo en la subida)
                if (image.getUrl() == null) {
                    return ResponseEntity.badRequest().build();
                }

                //Se asignan las imagenes al insumo
                promocion.getImagenes().add(image);
                //Se guarda la imagen en la base de datos
                imagenRepo.save(image);
                // Agregar la URL de la imagen a la lista de URLs subidas
                urls.add(image.getUrl());
            }

            //se actualiza el insumo en la base de datos con las imagenes
            baseRepository.save(promocion);

            // Convertir la lista de URLs a un objeto JSON y devolver como ResponseEntity con estado OK (200)
            return new ResponseEntity<>("{\"status\":\"OK\", \"urls\":" + urls + "}", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante el proceso de subida
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }

    // Método para eliminar una imagen por su identificador en la base de datos y en Cloudinary
    @Override
    public ResponseEntity<String> deleteImage(String publicId, Long id) {
        try {
            // Eliminar la imagen de la base de datos usando su identificador
            imagenRepo.deleteById(id);

            // Llamar al servicio de Cloudinary para eliminar la imagen por su publicId
            return cloudinaryService.deleteImage(publicId, id);

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante la eliminación
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Promocion save(PromocionDto promocion) {
        Promocion guardar = setearDatos(promocion);
        return promocionRepository.save(guardar);
    }

    @Override
    public List<Promocion> getPromocionesBySucursalId(Long id) {
        return promocionRepository.getPromocionesBySucursalId(id);
    }

    @Override
    public Promocion editarPromocion(PromocionDto promocion, Long id, LocalDate fechaActual) {
        Promocion fix = promocionRepository.getById(id);
        Promocion edit = editarDatos(promocion, fix, fechaActual);
        return promocionRepository.save(edit);
    }

    public Promocion setearDatos(PromocionDto dto) {
        Promocion promo = new Promocion();
        promo.setEliminado(dto.isEliminado());
        promo.setFechaBaja(LocalDate.of(9999,12,31));
        promo.setDenominacion(dto.getDenominacion());
        promo.setFechaDesde(dto.getFechaDesde());
        promo.setFechaHasta(dto.getFechaHasta());
        promo.setHoraDesde(dto.getHoraDesde());
        promo.setHoraHasta(dto.getHoraHasta());
        promo.setDescripcionDescuento(dto.getDescripcionDescuento());
        promo.setPrecioPromocional(dto.getPrecioPromocional());
        promo.setTipoPromocion(dto.getTipoPromocion());

        for (PromocionDetalleDto pd : dto.getPromocionDetalles()) {
            PromocionDetalle promoDet = new PromocionDetalle();
            promoDet.setEliminado(pd.isEliminado());
            promoDet.setFechaBaja(LocalDate.of(9999, 12, 31));
            promoDet.setCantidad(pd.getCantidad());
            promoDet.setArticulo(articuloRepository.getById(pd.getArticuloId()));
            promo.getPromocionDetalles().add(promoDet);
        }
        for (ImagenDto img : dto.getImagenes()) {
            ImagenPromocion imgPromo = new ImagenPromocion();
            imgPromo.setFechaBaja(LocalDate.of(9999, 12, 31));
            imgPromo.setEliminado(img.isEliminado());
            imgPromo.setUrl(img.getUrl());
            promo.getImagenes().add(imgPromo);
        }

        for (Long suc : dto.getSucursalesId()) {
            Sucursal sucursal = sucursalRepository.getById(suc);
            promo.getSucursales().add(sucursal);
            sucursal.getPromociones().add(promo);
        }
        return promo;
    }

    public Promocion editarDatos(PromocionDto dto, Promocion fix, LocalDate fechaActual) {
        promocionRepository.resetSucursales(fix.getId());
        fix.setEliminado(dto.isEliminado());
        fix.setFechaBaja(LocalDate.of(9999,12,31));
        fix.setDenominacion(dto.getDenominacion());
        fix.setFechaDesde(dto.getFechaDesde());
        fix.setFechaHasta(dto.getFechaHasta());
        fix.setHoraDesde(dto.getHoraDesde());
        fix.setHoraHasta(dto.getHoraHasta());
        fix.setDescripcionDescuento(dto.getDescripcionDescuento());
        fix.setPrecioPromocional(dto.getPrecioPromocional());
        fix.setTipoPromocion(dto.getTipoPromocion());

        for (PromocionDetalleDto pd : dto.getPromocionDetalles()) {
            if (pd.getId() == null) {
                PromocionDetalle promoDet = new PromocionDetalle();
                promoDet.setEliminado(pd.isEliminado());
                promoDet.setFechaBaja(LocalDate.of(9999, 12, 31));
                promoDet.setCantidad(pd.getCantidad());
                promoDet.setArticulo(articuloRepository.getById(pd.getArticuloId()));
                fix.getPromocionDetalles().add(promoDet);
            } else {
                PromocionDetalle promoDet = promocionDetalleRepository.getById2(pd.getId());
                promoDet.setEliminado(pd.isEliminado());
                promoDet.setCantidad(pd.getCantidad());
                promoDet.setArticulo(articuloRepository.getById(pd.getArticuloId()));
                if (promoDet.isEliminado() == true) {
                    promoDet.setFechaBaja(fechaActual);
                } else {
                    promoDet.setFechaBaja(LocalDate.of(9999, 12, 31));
                }
            }
        }

        for (ImagenDto img : dto.getImagenes()) {
            ImagenPromocion imgPromo = new ImagenPromocion();
            imgPromo.setFechaBaja(LocalDate.of(9999, 12, 31));
            imgPromo.setEliminado(img.isEliminado());
            imgPromo.setUrl(img.getUrl());
            fix.getImagenes().add(imgPromo);
        }

        for (Long suc : dto.getSucursalesId()) {
            Sucursal sucursal = sucursalRepository.getById(suc);
            fix.getSucursales().add(sucursal);
            sucursal.getPromociones().add(fix);
        }

        return fix;
    }
}
