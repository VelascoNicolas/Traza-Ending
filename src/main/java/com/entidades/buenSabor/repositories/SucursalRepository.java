package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Sucursal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SucursalRepository extends BaseRepository<Sucursal,Long> {
    @Query("SELECT s FROM Sucursal s LEFT JOIN FETCH s.promociones WHERE s.id = :id")
    Sucursal findWithPromocionesById(@Param("id") Long id);
    @Query("SELECT s FROM Sucursal s LEFT JOIN FETCH s.empleados WHERE s.id = :id")
    Sucursal findWithEmpleadosById(@Param("id") Long id);

    @Query("SELECT s FROM Sucursal s LEFT JOIN FETCH s.categorias WHERE s.id = :id")
    Sucursal findWithCategoriasById(@Param("id") Long id);

    @Query(value = "SELECT * FROM SUCURSAL WHERE EMPRESA_ID = ?1", nativeQuery = true)
    List<Sucursal> getAllByEmpresa(Long idEmpresa);

    @Query(value = "SELECT s.*, d.*, l.*, p.*\n" +
            "FROM SUCURSAL s\n" +
            "JOIN DOMICILIO d ON d.ID = s.DOMICILIO_ID\n" +
            "JOIN LOCALIDAD l ON d.LOCALIDAD_ID = l.ID\n" +
            "JOIN PROVINCIA p ON l.PROVINCIA_ID = p.ID\n" +
            "WHERE p.ID = ?1 AND l.ID = ?2 ", nativeQuery = true)
    List<Sucursal> getAllByUbicacion(Long idProvincia, Long idLocalidad);
}
