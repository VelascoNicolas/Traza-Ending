package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("/estadisticas")
public class EstadisticasController {

    @Autowired
    private EstadisticasService service;

    @GetMapping("/ranking")
    public ResponseEntity<?> rankin (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(service.bestProducts(fechaDesde, fechaHasta));
    }

    @GetMapping("/recaudacionesDiarias")
    public ResponseEntity<?> recaudacionesDiarias (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(service.ingresosDiarios(fechaDesde, fechaHasta));
    }

    @GetMapping("/recaudacionesMensuales")
    public ResponseEntity<?> recaudacionesMensuales (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(service.ingresosMensuales(fechaDesde, fechaHasta));
    }

    @GetMapping("/costosGanancias")
    public ResponseEntity<?> costosGanancias (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(service.findCostosGananciasByFecha(fechaDesde, fechaHasta));
    }

    @GetMapping("/pedidosCliente")
    public ResponseEntity<?> pedidosCliente (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(service.findCantidadPedidosPorCliente(fechaDesde, fechaHasta));
    }

    @GetMapping("/excelRanking")
    public ResponseEntity<?> excelRanking (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException {
        byte[] excelContent = service.rankingExcel(fechaDesde, fechaHasta);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticasRanking.xlsx");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelContent);
    }

    @GetMapping("/excelIDiario")
    public ResponseEntity<?> excelDiario (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException {
        byte[] excelContent = service.ingresosDiariosExcel(fechaDesde, fechaHasta);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticasIDiario.xlsx");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelContent);
    }

    @GetMapping("/excelIMensual")
    public ResponseEntity<?> excelMensual (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException {
        byte[] excelContent = service.ingresosMensualesExcel(fechaDesde, fechaHasta);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticasIMensual.xlsx");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelContent);
    }

    @GetMapping("/excelGanancias")
    public ResponseEntity<?> excelGanancias (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException {
        byte[] excelContent = service.gananciasNetasExcel(fechaDesde, fechaHasta);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticasGanancias.xlsx");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelContent);

    }
    @GetMapping("/excelPedidos")
    public ResponseEntity<byte[]> excelPedidos (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException {
        byte[] excelContent = service.cantidadPedidosPorClienteExcel(fechaDesde, fechaHasta);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticasPedidos.xlsx");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelContent);
    }
}
