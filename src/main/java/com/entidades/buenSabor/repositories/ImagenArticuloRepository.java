package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImagenArticuloRepository extends BaseRepository<ImagenArticulo,Long>{
    @Transactional
    @Modifying
    @Query(value = "UPDATE IMAGEN_ARTICULO SET ELIMINADO = true WHERE ARTICULO_ID = ?1", nativeQuery = true)
    void deleteLogico(Long articuloId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE IMAGEN_ARTICULO SET ELIMINADO = false WHERE ARTICULO_ID = ?1", nativeQuery = true)
    void altaLogica(Long articuloId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE IMAGEN_ARTICULO SET ELIMINADO = true WHERE ID = ?1", nativeQuery = true)
    void deleteImagen(Long id);
}
