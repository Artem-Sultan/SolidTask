package ru.sbt.bit.ood.solid.homework.reportNotifiers;

/**
 * Created by artem on 23.03.15.
 */
public class ReportMailFactory {
    private static final String subject = "Monthly department salary report";

    public static EMailObject createReportMail(String report, String recipients) {
        return new EMailObject(subject,report,recipients,null);
    }
}
