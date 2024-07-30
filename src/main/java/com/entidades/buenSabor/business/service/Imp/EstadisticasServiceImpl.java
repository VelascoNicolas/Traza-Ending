package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.EstadisticasService;
import com.entidades.buenSabor.domain.dto.ProyeccionesEstadisticas.*;
import com.entidades.buenSabor.domain.entities.DetallePedido;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.repositories.DetallePedidoRepository;
import com.entidades.buenSabor.repositories.PedidoRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class EstadisticasServiceImpl implements EstadisticasService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public List<RankingProductos> bestProducts(Date initialDate, Date endDate, Long idSucursal) {
        List<RankingProductos> ranking = detallePedidoRepository.mejoresProductos(initialDate, endDate, idSucursal);
        List<DetallePedido> detalles = detallePedidoRepository.getByFechasYSucursal(initialDate, endDate, idSucursal);
        List<RankingProductos> entrega = new ArrayList<>();
        Map<String, Integer> datos = new HashMap<>();
        int cont = 0;

        for (RankingProductos r : ranking) {
            String deno = r.getArticulo();
            Integer x = 0;
            for (DetallePedido detallePedido : detalles) {
                if (detallePedido.getPromocion() != null) {
                    cont++;
                    for (PromocionDetalle promoDetalle : detallePedido.getPromocion().getPromocionDetalles()) {
                        if (promoDetalle.getArticulo().getDenominacion().equalsIgnoreCase(deno)) {
                            x += detallePedido.getCantidad() * promoDetalle.getCantidad();
                            datos.put(deno, x);
                        } else {
                            x += detallePedido.getCantidad() * promoDetalle.getCantidad();
                            datos.put(promoDetalle.getArticulo().getDenominacion(), x);
                        }
                    }
                }
            }
        }
        if (cont > 0) {
            for (RankingProductos r: ranking) {
                String x = r.getArticulo();
                for (String nombre: datos.keySet()) {
                    if (nombre.equalsIgnoreCase(x)) {
                        int i = datos.get(nombre) + r.getCantidadTotal();
                        RankingProductos nuevito = new RankingProductos() {
                            @Override
                            public String getArticulo() {
                                return nombre;
                            }

                            @Override
                            public Integer getCantidadTotal() {
                                return i;
                            }
                        };
                        entrega.add(nuevito);
                    }
                }
            }
            return entrega;
        } else {
            return ranking;
        }
    }

    @Override
    public List<IngresosDiarios> ingresosDiarios(Date initialDate, Date endDate, Long idSucursal) {
        return pedidoRepository.ingresosDiarios(initialDate, endDate, idSucursal);
    }

    @Override
    public List<IngresosMenusales> ingresosMensuales(Date initialDate, Date endDate, Long idSucursal) {
        return pedidoRepository.ingresosMenusales(initialDate, endDate, idSucursal);
    }

    @Override
    public GananciasNetas findCostosGananciasByFecha(Date initialDate, Date endDate, Long idSucursal) {
        return pedidoRepository.gananciasNetas(initialDate, endDate, idSucursal);
    }

    @Override
    public List<PedidosCliente> findCantidadPedidosPorCliente(Date startDate, Date endDate, Long idSucursal) {
        return pedidoRepository.pedidosCliente(startDate, endDate, idSucursal);
    }

    @Override
    public byte[] rankingExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Ranking de comidas");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Denominacion", "Cantidad Vendida"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        List<RankingProductos> ranking = bestProducts(fechaDesde, fechaHasta, idSucursal);

        int rowNum = 1;
        for (RankingProductos r : ranking){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getArticulo());
            row.createCell(1).setCellValue(r.getCantidadTotal());
        }

        // Autosize columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }


        workbook.write(baos);
        workbook.close();

        return baos.toByteArray();
    }

    @Override
    public byte[] ingresosDiariosExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet2 = workbook.createSheet("Ingresos Diarios");

        // Crear encabezado
        Row headerRowIngresosDiarios = sheet2.createRow(0);
        String[] headersIngresosDiarios = {"Fecha", "Ingresos"};
        for (int i = 0; i < headersIngresosDiarios.length; i++) {
            Cell cell = headerRowIngresosDiarios.createCell(i);
            cell.setCellValue(headersIngresosDiarios[i]);
        }

        List<IngresosDiarios> ingresosDiarios = ingresosDiarios(fechaDesde, fechaHasta, idSucursal);

        int rowNum = 1;
        for (IngresosDiarios ingre : ingresosDiarios) {
            Row row = sheet2.createRow(rowNum++);
            row.createCell(0).setCellValue(ingre.getFecha());
            row.createCell(1).setCellValue(ingre.getIngresos());
        }

        // Autosize columns
        for (int i = 0; i < headersIngresosDiarios.length; i++) {
            sheet2.autoSizeColumn(i);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        return baos.toByteArray();
    }

    @Override
    public byte[] ingresosMensualesExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet3 = workbook.createSheet("Ingresos Mensuales");

        // Crear encabezado
        Row headerRowIngresosMensuales = sheet3.createRow(0);
        String[] headersIngresosMensuales = {"Mes", "Año", "Ingresos"};
        for (int i = 0; i < headersIngresosMensuales.length; i++) {
            Cell cell = headerRowIngresosMensuales.createCell(i);
            cell.setCellValue(headersIngresosMensuales[i]);
        }

        List<IngresosMenusales> ingresosMensuales = ingresosMensuales(fechaDesde, fechaHasta, idSucursal);

        int rowNum = 1;
        for (IngresosMenusales r : ingresosMensuales){
            Row row = sheet3.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getMes());
            row.createCell(1).setCellValue(r.getAño());
            row.createCell(2).setCellValue(r.getIngresos());
        }

        // Autosize columns
        for (int i = 0; i < headersIngresosMensuales.length; i++) {
            sheet3.autoSizeColumn(i);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        return baos.toByteArray();
    }

    @Override
    public byte[] gananciasNetasExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet5 = workbook.createSheet("Monto de Ganancia");

        // Crear encabezado
        Row headerRowGanancia= sheet5.createRow(0);
        String[] headersGanancia = {"Costo", "Ganancia", "Resultado"};
        for (int i = 0; i < headersGanancia.length; i++) {
            Cell cell = headerRowGanancia.createCell(i);
            cell.setCellValue(headersGanancia[i]);
        }

        GananciasNetas costoGanancias = findCostosGananciasByFecha(fechaDesde, fechaHasta, idSucursal);

        int rowNum = 1;
        Row row = sheet5.createRow(rowNum++);
        row.createCell(0).setCellValue((costoGanancias.getCosto() == null) ? 0 :costoGanancias.getCosto() );
        row.createCell(1).setCellValue((costoGanancias.getGanancias() == null) ? 0 : costoGanancias.getGanancias());
        row.createCell(2).setCellValue((costoGanancias.getResultado() == null ) ? 0 : costoGanancias.getResultado());


        // Autosize columns
        for (int i = 0; i < headersGanancia.length; i++) {
            sheet5.autoSizeColumn(i);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        return baos.toByteArray();
    }

    @Override
    public byte[] cantidadPedidosPorClienteExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet4 = workbook.createSheet("Pedidos por Cliente");

        // Crear encabezado
        Row headerRowPedidosClientes= sheet4.createRow(0);
        String[] headersPedidoClientes = {"Email cliente", "Cantidad de pedidos", "Fecha del Pedido"};
        for (int i = 0; i < headersPedidoClientes.length; i++) {
            Cell cell = headerRowPedidosClientes.createCell(i);
            cell.setCellValue(headersPedidoClientes[i]);
        }

        List<PedidosCliente> pedidosClientes = findCantidadPedidosPorCliente(fechaDesde, fechaHasta, idSucursal);

        int rowNum = 1;
        for (PedidosCliente r : pedidosClientes){
            Row row = sheet4.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getEmail());
            row.createCell(1).setCellValue(r.getTotalPedidos());
            row.createCell(2).setCellValue(r.getFecha());
        }

        // Autosize columns
        for (int i = 0; i < headersPedidoClientes.length; i++) {
            sheet4.autoSizeColumn(i);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        return baos.toByteArray();
    }
}
