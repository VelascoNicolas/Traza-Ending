package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.EMailService;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EMailServiceImpl implements EMailService {

    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendMail(byte[] file, String to) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(to);
            String[] Cc = new String[2];
            Cc[0] = "velasconico001@gmail.com";
            Cc[1] = "franco.silva1995@gmail.com";

            mimeMessageHelper.setCc(Cc);
            mimeMessageHelper.setSubject("Factura El Buen Sabor");
            mimeMessageHelper.setText("Le entregamos el archivo de la factura de su pedido. Disfrute!");

            if (file != null) {
                mimeMessageHelper.addAttachment(
                        "Factura.pdf",
                        new ByteArrayResource(file)
                );
            }

            javaMailSender.send(mimeMessage);
            return "Mail sent successfully";

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while sending email: " + e.getMessage(), e);
        }
    }
}
