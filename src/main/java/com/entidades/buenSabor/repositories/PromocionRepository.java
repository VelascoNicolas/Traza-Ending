package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Promocion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PromocionRepository extends BaseRepository<Promocion,Long>{
    @Query("SELECT p FROM Promocion p LEFT JOIN FETCH p.sucursales WHERE p.id = :id")
    Promocion findAllWithSucursales(@Param("id") Long id);

    @Query(value = "SELECT p.*\n" +
            "FROM PROMOCION p\n" +
            "JOIN SUCURSAL_PROMOCION sp ON p.ID = sp.PROMOCION_ID\n" +
            "WHERE sp.SUCURSAL_ID = :sucursal_id", nativeQuery = true)
    List<Promocion> getPromocionesBySucursalId(@Param("sucursal_id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SUCURSAL_PROMOCION WHERE PROMOCION_ID = :promocionId", nativeQuery = true)
    void resetSucursales(@Param("promocionId") Long promocionId);
}
