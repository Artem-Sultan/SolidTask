package ru.sbt.bit.ood.solid.homework.ReportConfigurators;

import ru.sbt.bit.ood.solid.homework.Containers.SalaryInfoEntry;

import java.util.Collection;

/**
 * Created by artem on 23.03.15.
 */
public interface ReportConfigurator {
    String configureReport(Collection<SalaryInfoEntry> salaryData);
}
