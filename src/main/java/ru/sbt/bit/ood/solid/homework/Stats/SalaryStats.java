package ru.sbt.bit.ood.solid.homework.Stats;

import ru.sbt.bit.ood.solid.homework.Containers.SalaryInfoEntry;

/**
 * Created by artem on 23.03.15.
 */
public interface SalaryStats {
    void addEntryForStats(SalaryInfoEntry salaryInfoEntry);
    double calculateStats();
}
