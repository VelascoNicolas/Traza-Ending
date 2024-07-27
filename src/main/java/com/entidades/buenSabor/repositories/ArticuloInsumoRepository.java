package com.entidades.buenSabor.repositories;


import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.Promocion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloInsumoRepository extends BaseRepository<ArticuloInsumo,Long> {

    @Query(value = "SELECT *\n" +
            "FROM ARTICULO_INSUMO ai\n" +
            "JOIN ARTICULO a ON ai.ID = a.ID\n" +
            "WHERE a.CATEGORIA_ID = ?1", nativeQuery = true)
    List<ArticuloInsumo> getArticulosByCategoria(Long idCategoria);

    @Query(value = "SELECT * FROM ARTICULO_INSUMO ai JOIN ARTICULO a ON ai.ID = a.ID WHERE ES_PARA_ELABORAR = TRUE", nativeQuery = true)
    List<ArticuloInsumo> getElaborados();
    @Query(value = "SELECT * FROM ARTICULO_INSUMO ai JOIN ARTICULO a ON ai.ID = a.ID WHERE ES_PARA_ELABORAR = FALSE", nativeQuery = true)
    List<ArticuloInsumo> getNoElaborados();

    @Query(value = "SELECT ai.*, a.*\n" +
            "FROM ARTICULO_INSUMO ai\n" +
            "INNER JOIN ARTICULO a ON ai.ID = a.ID\n" +
            "INNER JOIN STOCK_INSUMO_SUCURSAL si ON ai.ID = si.ARTICULO_INSUMO\n" +
            "WHERE si.SUCURSAL = ?1", nativeQuery = true)
    List<ArticuloInsumo> getArticuloInsumoBySucursal(Long idSucursal);

    @Query(value = "SELECT ai.*, a.*\n" +
            "FROM ARTICULO_INSUMO ai\n" +
            "INNER JOIN ARTICULO a ON ai.ID = a.ID\n" +
            "INNER JOIN STOCK_INSUMO_SUCURSAL si ON ai.ID = si.ARTICULO_INSUMO\n" +
            "WHERE si.SUCURSAL = ?1 AND a.CATEGORIA_ID = ?2", nativeQuery = true)
    List<ArticuloInsumo> getArticuloInsumoBySucursalAndCategoria(Long idSucursal, Long idCategoria);
}
