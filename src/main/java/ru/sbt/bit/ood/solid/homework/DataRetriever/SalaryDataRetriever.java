package ru.sbt.bit.ood.solid.homework.dataRetriever;

import ru.sbt.bit.ood.solid.homework.SalaryData;

import java.util.Collection;

/**
 * Created by artem on 23.03.15.
 */
public interface SalaryDataRetriever {
    Collection<SalaryData> getSalaryData(SalaryRequestParameters salaryRequestParameters);
}
