package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends BaseRepository<Empleado,Long> {
    @Query(value = "SELECT * FROM EMPLEADO WHERE SUCURSAL_ID = :id", nativeQuery = true)
    public List<Empleado> getBySucursalId(@Param("id") Long id);

    @Query(value = "SELECT * FROM EMPLEADO WHERE EMAIL = :userName", nativeQuery = true)
    public Empleado getByUserName(@Param("userName") String userName);

    @Query(value = "SELECT COUNT(*) FROM EMPLEADO WHERE ACTIVO = TRUE AND ROL = 'COCINERO'", nativeQuery = true)
    public Integer getCocinerosActivos();

    @Query(value = "SELECT * FROM EMPLEADO WHERE ACTIVO = FALSE", nativeQuery = true)
    public List<Empleado> getInactivos();
}
