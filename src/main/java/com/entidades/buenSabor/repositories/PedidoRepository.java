package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.dto.ProyeccionesEstadisticas.GananciasNetas;
import com.entidades.buenSabor.domain.dto.ProyeccionesEstadisticas.IngresosDiarios;
import com.entidades.buenSabor.domain.dto.ProyeccionesEstadisticas.IngresosMenusales;
import com.entidades.buenSabor.domain.dto.ProyeccionesEstadisticas.PedidosCliente;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.enums.Estado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long>{

    @Query(value = "SELECT * FROM PEDIDO WHERE USER_NAME = ?1", nativeQuery = true)
    List<Pedido> findByCliente_UserName(String userName);

    @Query(value = "SELECT * FROM PEDIDO WHERE estado =?1", nativeQuery = true)
    List<Pedido> findByEstado(Estado estado);

    @Query(value = "SELECT * FROM PEDIDO WHERE SUCURSAL_ID =?1", nativeQuery = true)
    List<Pedido> findBySucursal(Long idSucursal);

    @Query(value = "SELECT\n" +
            "  p.FECHA_PEDIDO AS Fecha,\n" +
            "  CAST(SUM(p.TOTAL) AS DECIMAL(10, 2)) AS Ingresos\n" +
            "FROM\n" +
            "  (SELECT DISTINCT p.ID, p.FECHA_PEDIDO, p.TOTAL, p.SUCURSAL_ID, p.ESTADO\n" +
            "   FROM Pedido p\n" +
            "   JOIN Detalle_Pedido dp ON dp.PEDIDO_ID = p.ID) p\n" +
            "WHERE\n" +
            "  p.FECHA_PEDIDO BETWEEN ?1 AND ?2\n" +
            "  AND p.SUCURSAL_ID = ?3\n" +
            "  AND p.ESTADO NOT IN (4, 5, 2)\n" +
            "GROUP BY\n" +
            "  p.FECHA_PEDIDO\n" +
            "ORDER BY\n" +
            "  p.FECHA_PEDIDO;", nativeQuery = true)
    List<IngresosDiarios> ingresosDiarios(Date initialDate, Date endDate, Long idSucursal);//AND p.SUCURSAL_ID = :idSucursal

    @Query(value = "SELECT\n" +
            "  EXTRACT(YEAR FROM p.FECHA_PEDIDO) AS Año,\n" +
            "  EXTRACT(MONTH FROM p.FECHA_PEDIDO) AS Mes,\n" +
            "  CAST(SUM(p.TOTAL) AS DECIMAL(10, 2)) AS Ingresos\n" +
            "FROM\n" +
            "  (SELECT DISTINCT p.ID, p.FECHA_PEDIDO, p.TOTAL, p.SUCURSAL_ID, p.ESTADO\n" +
            "   FROM Pedido p\n" +
            "   JOIN Detalle_Pedido dp ON dp.PEDIDO_ID = p.ID) p\n" +
            "WHERE\n" +
            "  p.FECHA_PEDIDO BETWEEN ?1 AND ?2\n" +
            "  AND p.SUCURSAL_ID = ?3\n" +
            "  AND p.ESTADO NOT IN (4, 5, 2)\n" +
            "GROUP BY\n" +
            "  EXTRACT(YEAR FROM p.FECHA_PEDIDO), EXTRACT(MONTH FROM p.FECHA_PEDIDO)\n" +
            "ORDER BY\n" +
            "  Año, Mes;", nativeQuery = true)
    List<IngresosMenusales> ingresosMenusales(Date initialDate, Date endDate, Long idSucursal);//AND p.SUCURSAL_ID = :idSucursal

    @Query(value = "SELECT \n" +
            "    c.USER_NAME AS Email,\n" +
            "    COUNT(p.ID) AS TotalPedidos,\n" +
            "    p.FECHA_PEDIDO AS Fecha\n" +
            "FROM \n" +
            "    Pedido p\n" +
            "JOIN \n" +
            "    Cliente c ON p.USER_NAME = c.USER_NAME\n" +
            "WHERE \n" +
            "    p.FECHA_PEDIDO BETWEEN ?1 AND ?2 AND p.SUCURSAL_ID = ?3 AND ESTADO != 4 AND ESTADO != 5 AND ESTADO != 2\n" +
            "GROUP BY \n" +
            "    c.USER_NAME, c.NOMBRE, c.APELLIDO, p.FECHA_PEDIDO\n" +
            "ORDER BY \n" +
            "    c.USER_NAME, p.FECHA_PEDIDO;\n", nativeQuery = true)
    List<PedidosCliente> pedidosCliente(Date initialDate, Date endDate, Long idSucursal);//AND p.SUCURSAL_ID = :idSucursal

    @Query(value = "SELECT\n" +
            "  CAST(SUM(total) AS DECIMAL(10, 2)) AS Ganancias,\n" +
            "  SUM(total_costo) AS Costo,\n" +
            "  SUM(total - total_costo) AS \"Resultado\"\n" +
            "FROM\n" +
            "  (SELECT DISTINCT ID, total, total_costo\n" +
            "   FROM Pedido\n" +
            "   WHERE FECHA_PEDIDO BETWEEN ?1 AND ?2\n" +
            "   AND SUCURSAL_ID = ?3\n" +
            "   AND ESTADO NOT IN (4, 5, 2)) AS subquery;", nativeQuery = true)
    GananciasNetas gananciasNetas(Date initialDate, Date endDate, Long idSucursal);
}
