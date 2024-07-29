package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.domain.dto.ProyeccionesEstadisticas.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EstadisticasService {
    List<RankingProductos> bestProducts(Date initialDate, Date endDate, Long idSucursal);
    List<IngresosDiarios> ingresosDiarios(Date initialDate, Date endDate, Long idSucursal);
    List<IngresosMenusales> ingresosMensuales(Date initialDate, Date endDate, Long idSucursal);
    GananciasNetas findCostosGananciasByFecha(Date initialDate, Date endDate, Long idSucursal);
    List<PedidosCliente> findCantidadPedidosPorCliente(Date startDate, Date endDate, Long idSucursal);
    byte[] rankingExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException;
    byte[] ingresosDiariosExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException;
    byte[] ingresosMensualesExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException;
    byte[] gananciasNetasExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException;
    byte[] cantidadPedidosPorClienteExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException;
}
