package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.business.service.PedidoService;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.dto.DetallePedidoDTO;
import com.entidades.buenSabor.domain.dto.PedidoDTO;
import com.entidades.buenSabor.domain.entities.*;
import com.entidades.buenSabor.domain.enums.Estado;
import com.entidades.buenSabor.domain.enums.TipoEnvio;
import com.entidades.buenSabor.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private StockInsumoSucursalRepository stockInsumoSucursalRepository;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    private PromocionService promocionService;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    @Transactional
    public List<Pedido> getTodos() {
        return pedidoRepository.findAll();
    }

    @Override
    @Transactional
    public Pedido getByID(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public Pedido guardarPedido(PedidoDTO pedido, Double precioDelivery) {
        Pedido save = new Pedido();
        save.setFechaPedido(pedido.getFechaPedido());
        save.setEliminado(pedido.isEliminado());
        save.setEstado(pedido.getEstado());
        save.setTipoEnvio(pedido.getTipoEnvio());
        save.setFormaPago(pedido.getFormaPago());
        save.setDomicilio(pedido.getDomicilio());
        save.setSucursal(pedido.getSucursal());
        save.setFactura(null);
        save.setCliente(pedido.getCliente());
        save.setEmpleado(pedido.getEmpleado());

        for(DetallePedidoDTO dp : pedido.getDetallePedidos()) {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setEliminado(dp.isEliminado());
            detallePedido.setCantidad(dp.getCantidad());
            detallePedido.setSubTotal(dp.getSubTotal());
            if (dp.getArticulo() != null) {
                detallePedido.setArticulo(articuloService.getById(dp.getArticulo()));
            }
            if (dp.getPromocion() != null) {
                detallePedido.setPromocion(promocionService.getById(dp.getPromocion()));
            }
            save.getDetallePedidos().add(detallePedido);
        }
        save.calcularPrecioVentaTotal(precioDelivery);
        save.calcularPrecioCostoTotal();
        manejoStock(save);
        save.setTiempoDeEspera(tiempoEstimado(save));
        return pedidoRepository.save(save);
    }

    @Override
    public Pedido agregarFactura(Long idPedido, Long idEmpleado) {
        Pedido pedido = getByID(idPedido);
        Double total = 0.0;
        Factura factura = new Factura();
        factura.setFechaFacturacion(pedido.getFechaPedido());
        factura.setFormaPago(pedido.getFormaPago());
        factura.setSubTotal(pedido.getTotal());
        if (pedido.getTipoEnvio() != TipoEnvio.DELIVERY) {
            for (DetallePedido detallePedido : pedido.getDetallePedidos()) {
                total += detallePedido.getSubTotal();
            }
            factura.setDescuento(total * 0.1);
        } else {
            factura.setDescuento(0.0);
        }
        factura.setTotal(pedido.getTotal());
        pedido.setFactura(factura);
        pedido.setEmpleado(empleadoRepository.findById(idEmpleado).orElse(null));
        pedido.setEstado(Estado.FACTURADO);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido actualizarEstado(Long idPedido, Estado estado) {
        Pedido pedido = getByID(idPedido);
        pedido.setEstado(estado);
        if (estado == Estado.CANCELADO || estado == Estado.RECHAZADO) {
            devolverStock(pedido);
        }
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> getPedidosByCliente(String userName) {
        return pedidoRepository.findByCliente_UserName(userName);
    }

    @Override
    public List<Pedido> getPedidosByEstado(Estado estado) {
        return pedidoRepository.findByEstado(estado);
    }

    @Override
    public List<Pedido> getPedidosBySucursal(Long idSucursal) {
        return pedidoRepository.findBySucursal(idSucursal);
    }

    @Override
    public boolean checkStockPromocion(Long idPromocion, PedidoDTO pedido) {
        Promocion promocion = promocionService.getById(idPromocion);
        boolean stock = true;

        Pedido save = new Pedido();
        save.setFechaPedido(pedido.getFechaPedido());
        save.setEliminado(pedido.isEliminado());
        save.setEstado(pedido.getEstado());
        save.setTipoEnvio(pedido.getTipoEnvio());
        save.setFormaPago(pedido.getFormaPago());
        save.setDomicilio(pedido.getDomicilio());
        save.setSucursal(pedido.getSucursal());
        save.setFactura(null);
        save.setCliente(pedido.getCliente());
        save.setEmpleado(pedido.getEmpleado());

        for(DetallePedidoDTO dp : pedido.getDetallePedidos()) {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setEliminado(dp.isEliminado());
            detallePedido.setCantidad(dp.getCantidad());
            detallePedido.setSubTotal(dp.getSubTotal());
            if (dp.getArticulo() != null) {
                detallePedido.setArticulo(articuloService.getById(dp.getArticulo()));
            }
            if (dp.getPromocion() != null) {
                detallePedido.setPromocion(promocionService.getById(dp.getPromocion()));
            }
            save.getDetallePedidos().add(detallePedido);

        }
        checkeoStock(save);

        for (PromocionDetalle pd : promocion.getPromocionDetalles()) {
            if (pd.getArticulo() instanceof ArticuloInsumo) {
                StockInsumoSucursal s1 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(save.getSucursal().getId(), pd.getArticulo().getId());
                if ( s1.getStockActual() - pd.getCantidad() < s1.getStockMinimo()) {
                    stock = false;
                }
            } else if (pd.getArticulo() instanceof ArticuloManufacturado) {
                for (ArticuloManufacturadoDetalle detalle : ((ArticuloManufacturado) pd.getArticulo()).getArticuloManufacturadoDetalles()) {
                    StockInsumoSucursal s2 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(save.getSucursal().getId(), detalle.getArticuloInsumo().getId());
                    if (s2.getStockActual() - pd.getCantidad() * detalle.getCantidad() < s2.getStockMinimo()) {
                        stock = false;
                    }
                }
            }
        }

        return stock;
    }

    @Override
    public boolean checkStockArticulo(Long idArticulo, PedidoDTO pedido) {
        Articulo articulo = articuloService.getById(idArticulo);
        boolean stock = true;

        Pedido save = new Pedido();
        save.setFechaPedido(pedido.getFechaPedido());
        save.setEliminado(pedido.isEliminado());
        save.setEstado(pedido.getEstado());
        save.setTipoEnvio(pedido.getTipoEnvio());
        save.setFormaPago(pedido.getFormaPago());
        save.setDomicilio(pedido.getDomicilio());
        save.setSucursal(pedido.getSucursal());
        save.setFactura(null);
        save.setCliente(pedido.getCliente());
        save.setEmpleado(pedido.getEmpleado());

        for(DetallePedidoDTO dp : pedido.getDetallePedidos()) {
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setEliminado(dp.isEliminado());
            detallePedido.setCantidad(dp.getCantidad());
            detallePedido.setSubTotal(dp.getSubTotal());
            if (dp.getArticulo() != null) {
                detallePedido.setArticulo(articuloService.getById(dp.getArticulo()));
            }
            if (dp.getPromocion() != null) {
                detallePedido.setPromocion(promocionService.getById(dp.getPromocion()));
            }
            save.getDetallePedidos().add(detallePedido);

        }
        checkeoStock(save);

        if (articulo instanceof ArticuloInsumo) {
            StockInsumoSucursal s1 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(save.getSucursal().getId(), articulo.getId());
            if (s1.getStockActual() - 1 < s1.getStockMinimo()) {
                stock = false;
            }
        } else if (articulo instanceof ArticuloManufacturado) {
            for (ArticuloManufacturadoDetalle detalle : ((ArticuloManufacturado) articulo).getArticuloManufacturadoDetalles()) {
                StockInsumoSucursal s2 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(save.getSucursal().getId(), detalle.getArticuloInsumo().getId());
                if (s2.getStockActual() - detalle.getCantidad() < s2.getStockMinimo()) {
                    stock = false;
                }
            }
        }

        return stock;
    }

    public void checkeoStock(Pedido pedido) {
        for (DetallePedido dp : pedido.getDetallePedidos()) {
            if (dp.getArticulo() != null) {
                if (dp.getArticulo() instanceof ArticuloInsumo) {
                    StockInsumoSucursal s1 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), dp.getArticulo().getId());
                    s1.setStockActual(s1.getStockActual() - dp.getCantidad());
                } else if (dp.getArticulo() instanceof ArticuloManufacturado) {
                    for (ArticuloManufacturadoDetalle detalle : ((ArticuloManufacturado) dp.getArticulo()).getArticuloManufacturadoDetalles()) {
                        StockInsumoSucursal s2 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), detalle.getArticuloInsumo().getId());
                        s2.setStockActual(s2.getStockActual() - (dp.getCantidad() * detalle.getCantidad()));
                    }
                }
            }
            if (dp.getPromocion() != null) {
                for (PromocionDetalle detalle : dp.getPromocion().getPromocionDetalles()) {
                    if (detalle.getArticulo() instanceof ArticuloInsumo) {
                        StockInsumoSucursal s3 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), detalle.getArticulo().getId());
                        s3.setStockActual(s3.getStockActual() - (dp.getCantidad() * detalle.getCantidad()));
                    } else if (detalle.getArticulo() instanceof ArticuloManufacturado) {
                        for (ArticuloManufacturadoDetalle detalle2 : ((ArticuloManufacturado) detalle.getArticulo()).getArticuloManufacturadoDetalles()) {
                            StockInsumoSucursal s4 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), detalle2.getArticuloInsumo().getId());
                            s4.setStockActual(s4.getStockActual() - (dp.getCantidad() * detalle.getCantidad() * detalle2.getCantidad()));
                        }
                    }
                }
            }
        }
    }

    public void devolverStock(Pedido pedido) {
        for (DetallePedido dp : pedido.getDetallePedidos()) {
            if (dp.getArticulo() != null) {
                if (dp.getArticulo() instanceof ArticuloInsumo) {
                    StockInsumoSucursal s1 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), dp.getArticulo().getId());
                    s1.setStockActual(s1.getStockActual() + dp.getCantidad());
                    articuloInsumoRepository.save((ArticuloInsumo) dp.getArticulo());
                    stockInsumoSucursalRepository.save(s1);
                } else if (dp.getArticulo() instanceof ArticuloManufacturado) {
                    for (ArticuloManufacturadoDetalle detalle : ((ArticuloManufacturado) dp.getArticulo()).getArticuloManufacturadoDetalles()) {
                        StockInsumoSucursal s2 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), detalle.getArticuloInsumo().getId());
                        s2.setStockActual(s2.getStockActual() + (dp.getCantidad() * detalle.getCantidad()));
                        articuloInsumoRepository.save(detalle.getArticuloInsumo());
                        stockInsumoSucursalRepository.save(s2);
                    }
                }
            }
            if (dp.getPromocion() != null) {
                for (PromocionDetalle detalle : dp.getPromocion().getPromocionDetalles()) {
                    if (detalle.getArticulo() instanceof ArticuloInsumo) {
                        StockInsumoSucursal s3 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), detalle.getArticulo().getId());
                        s3.setStockActual(s3.getStockActual() + (dp.getCantidad() * detalle.getCantidad()));
                        articuloInsumoRepository.save((ArticuloInsumo) detalle.getArticulo());
                        stockInsumoSucursalRepository.save(s3);
                    } else if (detalle.getArticulo() instanceof ArticuloManufacturado) {
                        for (ArticuloManufacturadoDetalle detalle2 : ((ArticuloManufacturado) detalle.getArticulo()).getArticuloManufacturadoDetalles()) {
                            StockInsumoSucursal s4 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), detalle2.getArticuloInsumo().getId());
                            s4.setStockActual(s4.getStockActual() + (dp.getCantidad() * detalle.getCantidad() * detalle2.getCantidad()));
                            articuloInsumoRepository.save(detalle2.getArticuloInsumo());
                            stockInsumoSucursalRepository.save(s4);
                        }
                    }
                }
            }
        }
    }

    public void manejoStock(Pedido pedido) {
        for (DetallePedido dp : pedido.getDetallePedidos()) {
            if (dp.getArticulo() != null) {
                if (dp.getArticulo() instanceof ArticuloInsumo) {
                    StockInsumoSucursal s1 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), dp.getArticulo().getId());
                    s1.setStockActual(s1.getStockActual() - dp.getCantidad());
                    articuloInsumoRepository.save((ArticuloInsumo) dp.getArticulo());
                    stockInsumoSucursalRepository.save(s1);
                } else if (dp.getArticulo() instanceof ArticuloManufacturado) {
                    for (ArticuloManufacturadoDetalle detalle : ((ArticuloManufacturado) dp.getArticulo()).getArticuloManufacturadoDetalles()) {
                        StockInsumoSucursal s2 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), detalle.getArticuloInsumo().getId());
                        s2.setStockActual(s2.getStockActual() - (dp.getCantidad() * detalle.getCantidad()));
                        articuloInsumoRepository.save(detalle.getArticuloInsumo());
                        stockInsumoSucursalRepository.save(s2);
                    }
                }
            }
            if (dp.getPromocion() != null) {
                for (PromocionDetalle detalle : dp.getPromocion().getPromocionDetalles()) {
                    if (detalle.getArticulo() instanceof ArticuloInsumo) {
                        StockInsumoSucursal s3 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), detalle.getArticulo().getId());
                        s3.setStockActual(s3.getStockActual() - (dp.getCantidad() * detalle.getCantidad()));
                        articuloInsumoRepository.save((ArticuloInsumo) detalle.getArticulo());
                        stockInsumoSucursalRepository.save(s3);
                    } else if (detalle.getArticulo() instanceof ArticuloManufacturado) {
                        for (ArticuloManufacturadoDetalle detalle2 : ((ArticuloManufacturado) detalle.getArticulo()).getArticuloManufacturadoDetalles()) {
                            StockInsumoSucursal s4 = stockInsumoSucursalRepository.getStockInsumoSucursalBySucursalAndInsumo(pedido.getSucursal().getId(), detalle2.getArticuloInsumo().getId());
                            s4.setStockActual(s4.getStockActual() - (dp.getCantidad() * detalle.getCantidad() * detalle2.getCantidad()));
                            articuloInsumoRepository.save(detalle2.getArticuloInsumo());
                            stockInsumoSucursalRepository.save(s4);
                        }
                    }
                }
            }
        }
    }

    public LocalTime tiempoEstimado(Pedido save) {
        Integer tiempo = 0;
        Integer actual = 0;
        Integer cocinerosActivos = empleadoRepository.getCocinerosActivos();
        for (DetallePedido dp : save.getDetallePedidos()) {
            if (dp.getArticulo() != null) {
                if(dp.getArticulo() instanceof ArticuloManufacturado) {
                    actual += ((ArticuloManufacturado) dp.getArticulo()).getTiempoEstimadoMinutos();
                }
            }
            if (dp.getPromocion() != null) {
                for (PromocionDetalle promocionDetalle : dp.getPromocion().getPromocionDetalles()) {
                    if (promocionDetalle.getArticulo() instanceof ArticuloManufacturado) {
                        ArticuloManufacturado a = (ArticuloManufacturado) promocionDetalle.getArticulo();
                        actual += a.getTiempoEstimadoMinutos();
                    }
                }
            }
        }
        List<Pedido> pedidosEnCocina = pedidoRepository.findByEstado(Estado.APROBADO);
        Integer cocina = 0;

        if (pedidosEnCocina != null) {
            for (Pedido p : pedidosEnCocina) {
                for (DetallePedido dp : p.getDetallePedidos()) {
                    if (dp.getArticulo() != null) {
                        if(dp.getArticulo() instanceof ArticuloManufacturado) {
                            cocina += ((ArticuloManufacturado) dp.getArticulo()).getTiempoEstimadoMinutos();
                        }
                    }
                    if (dp.getPromocion() != null) {
                        for (PromocionDetalle promocionDetalle : dp.getPromocion().getPromocionDetalles()) {
                            if (promocionDetalle.getArticulo() instanceof ArticuloManufacturado) {
                                ArticuloManufacturado b = (ArticuloManufacturado) promocionDetalle.getArticulo();
                                cocina += b.getTiempoEstimadoMinutos();
                            }
                        }
                    }
                }
            }
        }
        cocina = cocina / cocinerosActivos;
        if (save.getTipoEnvio() == TipoEnvio.DELIVERY) {
            tiempo = actual + cocina + 10;
        } else {
            tiempo = actual + cocina;
        }
        LocalTime horaEstimada = LocalTime.now();
        horaEstimada = horaEstimada.plusMinutes(tiempo);
        return horaEstimada;
    }
}
