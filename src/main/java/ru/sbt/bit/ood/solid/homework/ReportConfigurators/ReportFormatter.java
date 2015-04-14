package ru.sbt.bit.ood.solid.homework.reportConfigurators;

import ru.sbt.bit.ood.solid.homework.SalaryData;

import java.util.Collection;

/**
 * Created by artem on 23.03.15.
 */
public interface ReportFormatter {
    String configureReport(Collection<SalaryData> salaryData);
}
