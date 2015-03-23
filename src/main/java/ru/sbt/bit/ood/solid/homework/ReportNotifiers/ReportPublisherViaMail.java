package ru.sbt.bit.ood.solid.homework.ReportNotifiers;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by artem on 23.03.15.
 */
public class ReportPublisherViaMail implements ReportPublisher {
    private String recipients;

    public ReportPublisherViaMail(String recipients) {
        this.recipients = recipients;
    }

    @Override
    public void publishReport(String report) {
        try {
            mailToRecipients(report);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void mailToRecipients(String report) throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail.google.com");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);
        helper.setText(report, true);
        helper.setSubject("Monthly department salary report");
        mailSender.send(message);
    }
}
