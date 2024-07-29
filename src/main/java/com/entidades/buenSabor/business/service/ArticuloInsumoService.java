package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ArticuloInsumoService extends BaseService<ArticuloInsumo,Long> {
    String deleteLogico(Long id);
    List<ArticuloInsumo> getArticuloInsumoBySucursal(Long idSucursal);

    //Imagenes
    // Método para obtener todas las imágenes almacenadas
    ResponseEntity<List<Map<String, Object>>> getAllImagesByArticuloId(Long id);
    // Método para subir imágenes al sistema
    ResponseEntity<String> uploadImages(MultipartFile[] files, Long id);
    // Método para eliminar una imagen por su identificador público y Long
    ResponseEntity<String> deleteImage(String publicId, Long id);
    List<ArticuloInsumo> getElaborados();
    List<ArticuloInsumo> getNoElaborados(Long idSucursal);
    List<ArticuloInsumo> getBySucursalYCategoria(Long idSucursal, Long idCategoria);
}
