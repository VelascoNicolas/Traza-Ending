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

    @GetMapping("/ranking/{idSucursal}")
    public ResponseEntity<?> rankin ( @PathVariable Long idSucursal,
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(service.bestProducts(fechaDesde, fechaHasta, idSucursal));
    }

    @GetMapping("/recaudacionesDiarias/{idSucursal}")
    public ResponseEntity<?> recaudacionesDiarias (@PathVariable Long idSucursal,
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(service.ingresosDiarios(fechaDesde, fechaHasta, idSucursal));
    }

    @GetMapping("/recaudacionesMensuales/{idSucursal}")
    public ResponseEntity<?> recaudacionesMensuales (@PathVariable Long idSucursal,
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(service.ingresosMensuales(fechaDesde, fechaHasta, idSucursal));
    }

    @GetMapping("/costosGanancias/{idSucursal}")
    public ResponseEntity<?> costosGanancias (@PathVariable Long idSucursal,
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(service.findCostosGananciasByFecha(fechaDesde, fechaHasta, idSucursal));
    }

    @GetMapping("/pedidosCliente/{idSucursal}")
    public ResponseEntity<?> pedidosCliente (@PathVariable Long idSucursal,
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(service.findCantidadPedidosPorCliente(fechaDesde, fechaHasta, idSucursal));
    }

    @GetMapping("/excelRanking/{idSucursal}")
    public ResponseEntity<?> excelRanking (@PathVariable Long idSucursal,
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException {
        byte[] excelContent = service.rankingExcel(fechaDesde, fechaHasta, idSucursal);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticasRanking.xlsx");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelContent);
    }

    @GetMapping("/excelIDiario/{idSucursal}")
    public ResponseEntity<?> excelDiario (@PathVariable Long idSucursal,
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException {
        byte[] excelContent = service.ingresosDiariosExcel(fechaDesde, fechaHasta, idSucursal);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticasIDiario.xlsx");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelContent);
    }

    @GetMapping("/excelIMensual/{idSucursal}")
    public ResponseEntity<?> excelMensual (@PathVariable Long idSucursal,
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException {
        byte[] excelContent = service.ingresosMensualesExcel(fechaDesde, fechaHasta, idSucursal);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticasIMensual.xlsx");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelContent);
    }

    @GetMapping("/excelGanancias/{idSucursal}")
    public ResponseEntity<?> excelGanancias (@PathVariable Long idSucursal,
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException {
        byte[] excelContent = service.gananciasNetasExcel(fechaDesde, fechaHasta, idSucursal);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticasGanancias.xlsx");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelContent);

    }
    @GetMapping("/excelPedidos/{idSucursal}")
    public ResponseEntity<byte[]> excelPedidos (@PathVariable Long idSucursal,
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta) throws IOException {
        byte[] excelContent = service.cantidadPedidosPorClienteExcel(fechaDesde, fechaHasta, idSucursal);
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
