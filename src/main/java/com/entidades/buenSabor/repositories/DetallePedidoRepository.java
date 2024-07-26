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
            "     p.FECHA_PEDIDO BETWEEN ?1 AND ?2 \n" +
            "GROUP BY \n" +
            "    a.DENOMINACION\n" +
            "ORDER BY \n" +
            "    CantidadTotal DESC\n", nativeQuery = true)
    List<RankingProductos> mejoresProductos(Date initialDate, Date endDate);
}
