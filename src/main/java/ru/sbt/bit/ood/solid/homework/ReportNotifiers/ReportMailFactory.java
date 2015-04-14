package ru.sbt.bit.ood.solid.homework.reportNotifiers;

/**
 * Created by artem on 23.03.15.
 */
public class ReportMailFactory {
    private String recipients;
    private String report;
    private final String subject = "Monthly department salary report";

    public ReportMailFactory(String recipients, String report) {
        this.recipients = recipients;
        this.report = report;
    }

    public EMailObject createReportMail(String report, String recipients) {
        return new EMailObject(subject,report,recipients,null);
    }
}
