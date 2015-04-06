package ru.sbt.bit.ood.solid.homework;

import ru.sbt.bit.ood.solid.homework.Containers.RequestParameters;
import ru.sbt.bit.ood.solid.homework.Containers.SalaryInfoEntry;
import ru.sbt.bit.ood.solid.homework.DataRetriever.SalaryDataRetriever;
import ru.sbt.bit.ood.solid.homework.ReportConfigurators.ReportConfigurator;
import ru.sbt.bit.ood.solid.homework.ReportNotifiers.ReportPublisher;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by artem on 23.03.15.
 */
public class NotificationSystem {
    private SalaryDataRetriever salaryDataRetriever;
    private ReportConfigurator reportConfigurator;
    private ReportPublisher reportPublisher;

    public NotificationSystem(SalaryDataRetriever salaryDataRetriever, ReportConfigurator reportConfigurator, ReportPublisher reportPublisher) {
        this.salaryDataRetriever = salaryDataRetriever;
        this.reportConfigurator = reportConfigurator;
        this.reportPublisher = reportPublisher;
    }

    public void generateReport(String departmentId, LocalDate dateFrom, LocalDate dateTo){
        RequestParameters requestParameters = new RequestParameters(departmentId,dateFrom,dateTo);
        Collection<SalaryInfoEntry> salaryInfo = salaryDataRetriever.getSalaryData(requestParameters);
        String report = reportConfigurator.configureReport(salaryInfo);

        reportPublisher.publishReport(report);
    }
}
