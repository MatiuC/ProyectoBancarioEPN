package BussinesLogic.ApiRequest;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarMail {
    private static final String EMAIL_FROM = "polibancoepn@gmail.com";
    private static final String PASSWORD_FROM = "zgxymfgyvjfcspwj";
    
    public void enviarCredenciales(String emailTo, String usuario, String clave) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, PASSWORD_FROM);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject("Credenciales de acceso - PoliBanco");
            
            String contenido = String.format(
                "<html><body>" +
                "<h2>Bienvenido a PoliBanco</h2>" +
                "<p>Sus credenciales de acceso son:</p>" +
                "<p><b>Usuario:</b> %s</p>" +
                "<p><b>Contraseña:</b> %s</p>" +
                "<p>Por favor, cambie su contraseña en su primer inicio de sesión.</p>" +
                "</body></html>",
                usuario, clave
            );
            
            message.setText(contenido, "UTF-8", "html");
            Transport.send(message);
            
        } catch (MessagingException e) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }
}
