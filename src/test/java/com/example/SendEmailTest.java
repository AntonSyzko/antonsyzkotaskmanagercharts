package com.scrummater.tool;

import com.scrummater.tool.entity.Task;
import org.junit.Test;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by Admin on 23.10.2016.
 */
public class SendEmailTest {

    @Async
    @Test
    public void sendNotificaitoinTest() throws MailException, InterruptedException {

         JavaMailSender javaMailSender = new JavaMailSender() {
             @Override
             public MimeMessage createMimeMessage() {
                 return null;
             }

             @Override
             public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
                 return null;
             }

             @Override
             public void send(MimeMessage mimeMessage) throws MailException {

             }

             @Override
             public void send(MimeMessage... mimeMessages) throws MailException {

             }

             @Override
             public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {

             }

             @Override
             public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {

             }

             @Override
             public void send(SimpleMailMessage simpleMessage) throws MailException {

             }

             @Override
             public void send(SimpleMailMessage... simpleMessages) throws MailException {

             }
         };



        System.out.println("Sleeping now...");
        Thread.sleep(1000);

        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("antonsyzko@gmail.com");
        mail.setFrom("antonio.shizko@gmail.com");
        mail.setSubject("Task for " + new Date());
        mail.setText(" TEST EMAIL");
        javaMailSender.send(mail);

        System.out.println("Email Sent!");
    }
}
