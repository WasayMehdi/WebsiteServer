package com.wasaymehdi.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailClient {

    private static final String USERNAME = System.getProperty("com.wasaymehdi.email.username", "swm321@gmail.com");
    private static final String PASSWORD = System.getProperty("com.wasaymehdi.email.password");

    private final Session session;

    public EmailClient() {
        final Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        this.session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
    }

    public void send(final Email email) throws MessagingException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email.getFrom()));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(email.getTo())
        );
        message.setSubject(email.getSubject());
        message.setText(email.getContent());

        Transport.send(message);

    }
}
