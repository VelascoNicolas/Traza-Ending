package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.dto.ProyeccionesEstadisticas.RankingProductos;
import com.entidades.buenSabor.domain.entities.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido,Long>{

    @Query(value = "SELECT \n" +
            "    a.DENOMINACION AS Articulo,\n" +
            "    SUM(dp.CANTIDAD) AS CantidadTotal\n" +
            "FROM \n" +
            "    Detalle_Pedido dp\n" +
            "JOIN \n" +
            "    Pedido p ON dp.PEDIDO_ID = p.ID\n" +
            "JOIN \n" +
            "    Articulo a ON dp.ARTICULO_ID = a.ID\n" +
            "WHERE \n" +
            "     p.FECHA_PEDIDO BETWEEN ?1 AND ?2 AND p.SUCURSAL_ID = ?3 AND ESTADO != 4 AND ESTADO != 5 AND ESTADO != 2\n" +
            "GROUP BY \n" +
            "    a.DENOMINACION\n" +
            "ORDER BY \n" +
            "    CantidadTotal DESC\n", nativeQuery = true)
    List<RankingProductos> mejoresProductos(Date initialDate, Date endDate, Long idSucursal);

    @Query(value = "SELECT dp.ID, dp.ELIMINADO, dp.FECHA_BAJA, dp.CANTIDAD, dp.SUB_TOTAL, dp.ARTICULO_ID, dp.PROMOCION_ID, dp.PEDIDO_ID\n" +
            "FROM DETALLE_PEDIDO dp\n" +
            "INNER JOIN PEDIDO p ON dp.PEDIDO_ID = p.ID\n" +
            "WHERE p.FECHA_PEDIDO BETWEEN :fechaInicio AND :fechaFin\n" +
            "  AND p.SUCURSAL_ID = :sucursalId \n" +
            "AND p.ESTADO != 4 AND p.ESTADO != 5 AND p.ESTADO != 2", nativeQuery = true)
    List<DetallePedido> getByFechasYSucursal(Date fechaInicio, Date fechaFin, Long sucursalId);
}
