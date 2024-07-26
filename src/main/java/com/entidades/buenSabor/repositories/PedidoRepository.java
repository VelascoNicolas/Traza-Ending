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

    @Query(value = "SELECT \n" +
            "    p.FECHA_PEDIDO AS Fecha,\n" +
            "    CAST(SUM(dp.SUB_TOTAL) AS DECIMAL(10, 2)) AS Ingresos\n" +
            "FROM \n" +
            "    Detalle_Pedido dp\n" +
            "JOIN \n" +
            "    Pedido p ON dp.PEDIDO_ID = p.ID\n" +
            "WHERE \n" +
            "    p.FECHA_PEDIDO BETWEEN :initialDate AND :endDate \n" +
            "GROUP BY \n" +
            "    p.FECHA_PEDIDO\n" +
            "ORDER BY \n" +
            "    p.FECHA_PEDIDO;\n", nativeQuery = true)
    List<IngresosDiarios> ingresosDiarios(Date initialDate, Date endDate);

    @Query(value = "SELECT \n" +
            "    EXTRACT(YEAR FROM p.FECHA_PEDIDO) AS Año,\n" +
            "    EXTRACT(MONTH FROM p.FECHA_PEDIDO) AS Mes,\n" +
            "    CAST(SUM(dp.SUB_TOTAL) AS DECIMAL(10, 2)) AS Ingresos\n" +
            "FROM \n" +
            "    Detalle_Pedido dp\n" +
            "JOIN \n" +
            "    Pedido p ON dp.PEDIDO_ID = p.ID\n" +
            "WHERE \n" +
            "    p.FECHA_PEDIDO BETWEEN :initialDate AND :endDate\n" +
            "GROUP BY \n" +
            "    EXTRACT(YEAR FROM p.FECHA_PEDIDO), EXTRACT(MONTH FROM p.FECHA_PEDIDO)\n" +
            "ORDER BY \n" +
            "    Año, Mes;\n", nativeQuery = true)
    List<IngresosMenusales> ingresosMenusales(Date initialDate, Date endDate);

    @Query(value = "SELECT \n" +
            "    c.USER_NAME AS Email,\n" +
            "    COUNT(p.ID) AS TotalPedidos,\n" +
            "FROM \n" +
            "    Pedido p\n" +
            "JOIN \n" +
            "    Cliente c ON p.USER_NAME = c.USER_NAME\n" +
            "WHERE \n" +
            "    p.FECHA_PEDIDO BETWEEN :initialDate AND :endDate\n" +
            "GROUP BY \n" +
            "    c.USER_NAME, c.NOMBRE, c.APELLIDO, p.FECHA_PEDIDO\n" +
            "ORDER BY \n" +
            "    c.USER_NAME, p.FECHA_PEDIDO;\n", nativeQuery = true)
    List<PedidosCliente> pedidosCliente(Date initialDate, Date endDate);

    @Query(value = "SELECT CAST(sum(total) as DECIMAL(10, 2) )as Ganancias  , sum(total_costo)as Costo, sum(total - total_costo) as \"Resultado\"\n" +
            "from pedido WHERE FECHA_PEDIDO BETWEEN '2024-06-01' AND '2024-06-30'", nativeQuery = true)
    GananciasNetas gananciasNetas(Date initialDate, Date endDate);
}
