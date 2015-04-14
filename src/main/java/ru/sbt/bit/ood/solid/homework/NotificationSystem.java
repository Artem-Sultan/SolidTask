package ru.sbt.bit.ood.solid.homework;

import ru.sbt.bit.ood.solid.homework.dataRetriever.SalaryDataRetriever;
import ru.sbt.bit.ood.solid.homework.dataRetriever.SalaryRequestParameters;
import ru.sbt.bit.ood.solid.homework.reportConfigurators.ReportFormatter;
import ru.sbt.bit.ood.solid.homework.reportNotifiers.MailSender;

import java.time.LocalDate;
import java.util.Collection;

import static ru.sbt.bit.ood.solid.homework.reportNotifiers.ReportMailFactory.createReportMail;

/**
 * Created by artem on 23.03.15.
 */
public class NotificationSystem {
    private SalaryDataRetriever salaryDataRetriever;
    private ReportFormatter reportFormatter;
    private MailSender mailSender;

    public NotificationSystem(SalaryDataRetriever salaryDataRetriever, ReportFormatter reportFormatter, MailSender mailSender) {
        this.salaryDataRetriever = salaryDataRetriever;
        this.reportFormatter = reportFormatter;
        this.mailSender = mailSender;
    }

    public void generateReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients){
        SalaryRequestParameters salaryRequestParameters = new SalaryRequestParameters(departmentId,dateFrom,dateTo);
        Collection<SalaryData> salaryInfo = salaryDataRetriever.getSalaryData(salaryRequestParameters);
        String report = reportFormatter.configureReport(salaryInfo);
        mailSender.sendEMAil(createReportMail(report, recipients));
    }
}
