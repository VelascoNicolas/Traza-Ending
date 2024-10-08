package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.FacturaService;
import com.entidades.buenSabor.domain.entities.DetallePedido;
import com.entidades.buenSabor.domain.entities.Factura;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.enums.TipoEnvio;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

@Service
public class FacturaServiceImpl extends BaseServiceImp<Factura, Long> implements FacturaService {

    @Override
    public byte[] generarFacturaPDF(Pedido pedido) throws IOException {
        ClassPathResource pdfTemplateResource = new ClassPathResource("template/factura.pdf");
        InputStream inputStream = pdfTemplateResource.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfReader reader = new PdfReader(inputStream);
        PdfWriter writer = new PdfWriter(baos);
        String fecha = String.valueOf(pedido.getFechaPedido());
        String[] partesFecha = fecha.split("-");
        Double total = 0.0;
        for (DetallePedido detallePedido : pedido.getDetallePedidos()) {
            total += detallePedido.getSubTotal();
        }
        String fechaConEspacios = "";

        for (int i = 0; i < partesFecha.length; i++) {
            if (i > 0) {
                fechaConEspacios += "    " + partesFecha[i];
            } else {
                fechaConEspacios += partesFecha[i];
            }
        }

        //Crea el PDF
        PdfDocument pdfDoc = new PdfDocument(reader, writer);

        //Obtener la primera pagina
        PdfPage page = pdfDoc.getFirstPage();

        // Obtener el lienzo de la página para dibujar
        PdfCanvas canvas = new PdfCanvas(page);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        //x=derecha,izq y=arriba abajo
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(120, 611)
                .showText(" " + pedido.getCliente().getNombre()+ " " +pedido.getCliente().getApellido() )
                .endText();
        //domicilo
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(120, 587)
                .showText(String.valueOf(pedido.getDomicilio().getCalle()+" " + pedido.getDomicilio().getNumero()))
                .endText();
        //telefono
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(400, 587)
                .showText(pedido.getCliente().getTelefono())
                .endText();
        //fecha
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(107, 740)
                .showText(fechaConEspacios)
                .endText();
        if (pedido.getTipoEnvio() == TipoEnvio.TAKE_AWAY) {
            //total
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(460, 125)
                    .showText(String.valueOf(pedido.getTotal()))
                    .endText();
        } else {
            //total
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(460, 125)
                    .showText(String.valueOf(pedido.getTotal()))
                    .endText();
        }

        //detalles
        int y = 515; // Posición inicial en Y para los detalles usar siempre el y
        Set<DetallePedido> detalles = pedido.getDetallePedidos();
        for (DetallePedido detalle : detalles) {
            if (detalle.getArticulo() != null) {
                //cantidad
                canvas.beginText()
                        .setFontAndSize(font, 15)
                        .moveText(83, y)
                        .showText(String.valueOf(detalle.getCantidad()))
                        .endText();
                //descripcion
                canvas.beginText()
                        .setFontAndSize(font, 15)
                        .moveText(123, y)
                        .showText( detalle.getArticulo().getDenominacion())
                        .endText();
                //precio
                canvas.beginText()
                        .setFontAndSize(font, 15)
                        .moveText(460, y)
                        .showText(String.valueOf(detalle.getArticulo().getPrecioVenta() * detalle.getCantidad()))
                        .endText();
                y -= 20; // Decrementar la posición en Y para el siguiente detalle
            }
            if (detalle.getPromocion() != null) {
                //cantidad
                canvas.beginText()
                        .setFontAndSize(font, 15)
                        .moveText(83, y)
                        .showText(String.valueOf(detalle.getCantidad()))
                        .endText();
                //descripcion
                canvas.beginText()
                        .setFontAndSize(font, 15)
                        .moveText(123, y)
                        .showText( detalle.getPromocion().getDenominacion())
                        .endText();
                //precio
                canvas.beginText()
                        .setFontAndSize(font, 15)
                        .moveText(460, y)
                        .showText(String.valueOf(detalle.getPromocion().getPrecioPromocional() * detalle.getCantidad()))
                        .endText();
                y -= 20; // Decrementar la posición en Y para el siguiente detalle
            }
        }
        y -= 3;
        if (pedido.getTipoEnvio() == TipoEnvio.DELIVERY) {
            //cantidad
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(83, y)
                    .showText("1")
                    .endText();
            //descripcion
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(123, y)
                    .showText( "Delivery")
                    .endText();
            //precio
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(460, y)
                    .showText(String.valueOf((pedido.getTotal() - pedido.calcularPrecioVentaTotal(0.0))))
                    .endText();
        } else {
            //cantidad
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(83, y)
                    .showText("1")
                    .endText();
            //descripcion
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(123, y)
                    .showText( "Descuento")
                    .endText();
            //precio
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(460, y)
                    .showText(String.valueOf(total * 0.1))
                    .endText();
        }

        // Cerrar el documento
        pdfDoc.close();

        return baos.toByteArray();

    }
}
