package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.Promocion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado,Long> {

    @Query("SELECT a FROM ArticuloManufacturado a LEFT JOIN FETCH a.sucursales WHERE a.id = :id")
    ArticuloManufacturado findAllWithSucursales(@Param("id") Long id);

    @Query(value = "SELECT EXISTS(\n" +
            "    SELECT 1\n" +
            "    FROM PROMOCION_DETALLE pd\n" +
            "    WHERE pd.ARTICULO_ID = ?1 AND ELIMINADO = FALSE\n" +
            ");", nativeQuery = true)
    boolean contiene(Long id);

    @Query(value = "SELECT * FROM ARTICULO_MANUFACTURADO am JOIN ARTICULO a ON am.ID = a.ID JOIN SUCURSAL_MANUFACTURADO sm ON sm.ARTICULO_MANUFACTURADO_ID = am.ID WHERE sm.SUCURSAL_ID = ?1", nativeQuery = true)
    List<ArticuloManufacturado> findBySucursalId(Long sucursalId);
}
