package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.domain.dto.PedidoDTO;
import com.entidades.buenSabor.domain.entities.Factura;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.enums.Estado;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface PedidoService {
    public List<Pedido> getTodos();
    public Pedido getByID(Long id);
    public Pedido guardarPedido(PedidoDTO pedido, Double precioDelivery);
    public Pedido agregarFactura(Long idPedido, Long idEmpleado);
    public Pedido actualizarEstado(Long idPedido, Estado estado);
    public List<Pedido> getPedidosByCliente(String userName);
    public List<Pedido> getPedidosByEstado(Estado estado);
    public List<Pedido> getPedidosBySucursal(Long idSucursal);
    public boolean checkStockPromocion(Long idPromocion, PedidoDTO pedido);
    public boolean checkStockArticulo(Long idArticulo, PedidoDTO pedido);
}
