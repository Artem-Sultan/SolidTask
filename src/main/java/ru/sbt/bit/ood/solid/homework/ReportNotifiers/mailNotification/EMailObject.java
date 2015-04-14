package ru.sbt.bit.ood.solid.homework.reportNotifiers.mailNotification;

/**
 * Created by artem on 14.04.15.
 */
public class EMailObject {
    private final String subject;
    private final String message;
    private final String recipients;
    //TODO attach bytestream
    private final String attachments;

    public EMailObject(String subject, String message, String recipients, String attachments) {
        this.subject = subject;
        this.message = message;
        this.recipients = recipients;
        this.attachments = attachments;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipients() {
        return recipients;
    }

    public String getAttachments() {
        return attachments;
    }
}
