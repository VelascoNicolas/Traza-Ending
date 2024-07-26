package com.entidades.buenSabor.presentation.rest;


import com.entidades.buenSabor.business.service.FacturaService;
import com.entidades.buenSabor.business.service.PedidoService;
import com.entidades.buenSabor.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.io.IOException;

@RestController
@RequestMapping("/facturas")
@CrossOrigin("*")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{pedidoId}")
    public ResponseEntity<byte[]> descargarFactura(@PathVariable Long pedidoId) throws IOException {
        Pedido pedido = pedidoService.getByID(pedidoId);
        byte[] pdfContent = facturaService.generarFacturaPDF(pedido);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "factura_" + pedidoId + ".pdf");
        headers.setContentLength(pdfContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
