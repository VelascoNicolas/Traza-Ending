package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.EMailService;
import com.entidades.buenSabor.business.service.FacturaService;
import com.entidades.buenSabor.business.service.PedidoService;
import com.entidades.buenSabor.domain.dto.PedidoDTO;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.entities.PreferenceMP;
import com.entidades.buenSabor.domain.enums.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private EMailService eMailService;

    @GetMapping
    public ResponseEntity<List<?>> getPedidos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getTodos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonList("ERROR FATAL PEDIDOS"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidoById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getByID(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR FATAL PEDIDO ID");
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarPedido(@RequestBody PedidoDTO pedido,
                                           @RequestParam(value = "precioDelivery", required = true) Double precioDelivery) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.guardarPedido(pedido, precioDelivery));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR FATAL PEDIDO");
        }
    }

    @GetMapping(path="/llamarMercadoPago/{id}")
    public ResponseEntity<?> createPreferenciaMercadoPago(@PathVariable Long id){
        MercadoPagoController MPController = new MercadoPagoController();
        try{
            Pedido pedido=pedidoService.getByID(id);
            PreferenceMP preference =MPController.getPreferenciaMercadoPago(pedido);
            return ResponseEntity.ok(preference);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error Fatal Create Preference MP");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> agregarFactura(@PathVariable Long id, @RequestParam Long idEmpleado) {
        try {
            pedidoService.agregarFactura(id, idEmpleado);
            Pedido pedido = pedidoService.getByID(id);
            byte[] pdfContent = facturaService.generarFacturaPDF(pedido);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "factura_" + id + ".pdf");
            headers.setContentLength(pdfContent.length);

            eMailService.sendMail(pdfContent, pedido.getCliente().getUserName());
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR FATAL FACTURA");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarPedido(@PathVariable Long id, @RequestBody Estado estado) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.actualizarEstado(id, estado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR FATAL ACTUALIZAR PEDIDO");
        }
    }

    @GetMapping("/sucursal/{idSucursal}")
    public ResponseEntity<?> getPedidosBySucursal(@PathVariable Long idSucursal) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getPedidosBySucursal(idSucursal));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonList("ERROR FATAL PEDIDO ID"));
        }
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<?>> getPedidosByCliente(@RequestParam(value = "userName", required = true) String userName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getPedidosByCliente(userName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonList("ERROR FATAL PEDIDO ID"));
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<?> getPedidosByEstado(@RequestBody Estado estado) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getPedidosByEstado(estado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR FATAL PEDIDO ID");
        }
    }

    @PostMapping("/stockPromo/{idPromocion}")
    public ResponseEntity<?> getStockByPromocion(@PathVariable Long idPromocion, @RequestBody PedidoDTO pedido) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.checkStockPromocion(idPromocion, pedido));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR FATAL PEDIDO ID");
        }
    }

    @PostMapping("/stockArticulo/{idArticulo}")
    public ResponseEntity<?> getStockByArticulo(@PathVariable Long idArticulo, @RequestBody PedidoDTO pedido) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.checkStockArticulo(idArticulo, pedido));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR FATAL PEDIDO ID");
        }
    }
}