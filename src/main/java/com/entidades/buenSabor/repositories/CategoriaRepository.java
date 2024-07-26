package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Categoria;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria,Long>{
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.sucursales WHERE c.id = :id")
    Categoria findWithSucursalesById(@Param("id") Long id);

    @Query(value = "SELECT EXISTS(\n" +
            "    SELECT 1\n" +
            "    FROM ARTICULO\n" +
            "    WHERE CATEGORIA_ID = ?1 AND ELIMINADO = FALSE\n" +
            ");", nativeQuery = true)
    boolean existe(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SUCURSAL_CATEGORIA WHERE CATEGORIA_ID = ?1", nativeQuery = true)
    void deleteSucursalCategoria(Long idCategoria);

    @Query(value = "SELECT c.ID, c.FECHA_BAJA, c.ELIMINADO, c.DENOMINACION, c.CATEGORIA_ID\n" +
            "FROM CATEGORIA c\n" +
            "JOIN SUCURSAL_CATEGORIA sc ON c.ID = sc.CATEGORIA_ID\n" +
            "JOIN SUCURSAL s ON sc.SUCURSAL_ID = s.ID\n" +
            "WHERE s.ID = ?1 AND c.CATEGORIA_ID IS NULL", nativeQuery = true)
    List<Categoria> getCategoriasPadreBySucursal(Long idSucursal);

    @Query(value = "SELECT c.ID, c.FECHA_BAJA, c.ELIMINADO, c.DENOMINACION, c.CATEGORIA_ID\n" +
            "FROM CATEGORIA c\n" +
            "JOIN SUCURSAL_CATEGORIA sc ON c.ID = sc.CATEGORIA_ID\n" +
            "JOIN SUCURSAL s ON sc.SUCURSAL_ID = s.ID\n" +
            "WHERE s.ID = ?1", nativeQuery = true)
    List<Categoria> getCategoriasBySucursal(Long idSucursal);

    @Query(value = "SELECT * FROM CATEGORIA WHERE CATEGORIA_ID IS NULL", nativeQuery = true)
    List<Categoria> getAllCategoriasPadre();

    @Query(value = "SELECT * FROM CATEGORIA WHERE CATEGORIA_ID = ?1", nativeQuery = true)
    List<Categoria> getHijasByPadre(Long idCategoriaPadre);
}
