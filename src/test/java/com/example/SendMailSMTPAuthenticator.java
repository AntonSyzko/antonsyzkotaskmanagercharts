package com.example;


import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Admin on 24.10.2016.
 */
public class SendMailSMTPAuthenticator {





    public class SMTPAuthenticator extends javax.mail.Authenticator {}
    @Test
    public  void maiilTest() {
        final String senderEmailID = "sender@gmail.com";
        final String senderPassword = "senderpass";
        final String emailSMTPserver = "smtp.gmail.com";
        final String emailServerPort = "465";
        String receiverEmailID = "receiver@gmail.com";
         String emailSubject = "Test Mail";
         String emailBody = ":)";
        Properties props = new Properties();
        props.put("mail.smtp.user",senderEmailID);
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", emailServerPort);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        PasswordAuthentication pa = new PasswordAuthentication(senderEmailID, senderPassword);
        SecurityManager security = System.getSecurityManager();
        try
        {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(emailBody);
            msg.setSubject(emailSubject);
            msg.setFrom(new InternetAddress(senderEmailID));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiverEmailID));
            Transport.send(msg,senderEmailID,senderPassword);
            System.out.println("Message send Successfully:)");
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
        }
        SendMailSMTPAuthenticator mailSender;
        mailSender = new SendMailSMTPAuthenticator();

    }

}
