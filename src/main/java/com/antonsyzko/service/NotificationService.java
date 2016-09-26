package com.antonsyzko.service;


import com.antonsyzko.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Admin on 21.09.2016.
 */
@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendNotificaitoin(Task task) throws MailException, InterruptedException {



        System.out.println("Sleeping now...");
        Thread.sleep(1000);

        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(task.getEmail());
        mail.setFrom("antonio.shizko@gmail.com");
        mail.setSubject("Task for " + new Date());
        System.out.println(task.getCreatedAt());
        mail.setText("\r\nYour current task for today is \r\n" + task.getDescription() + "\r\n priority: " +
                task.getTaskPriority() + "\r\n current Status: " + task.getTaskStatus()+
        "\r\n Task has to be done due to "+task.getDueTo());
        javaMailSender.send(mail);

        System.out.println("Email Sent!");
    }
}
