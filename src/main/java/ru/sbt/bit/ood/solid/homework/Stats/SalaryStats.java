package ru.sbt.bit.ood.solid.homework.stats;

import ru.sbt.bit.ood.solid.homework.SalaryData;

/**
 * Created by artem on 23.03.15.
 */
public interface SalaryStats {
    void addEntryForStats(SalaryData salaryData);
    double calculateStats();
}
