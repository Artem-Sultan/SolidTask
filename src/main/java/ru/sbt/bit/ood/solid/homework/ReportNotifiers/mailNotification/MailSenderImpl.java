package ru.sbt.bit.ood.solid.homework.reportNotifiers.mailNotification;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by artem on 14.04.15.
 */
public class MailSenderImpl implements MailSender {
    private final String mailHost = "mail.google.com";


    @Override
    public void sendEMAil(EMailObject eMailObject) {
        try {
            sendMail(eMailObject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void sendMail(EMailObject eMailObject) throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(eMailObject.getRecipients());
        helper.setText(eMailObject.getMessage(), true);
        helper.setSubject(eMailObject.getSubject());
        mailSender.send(message);
    }
}
