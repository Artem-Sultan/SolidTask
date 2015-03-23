package ru.sbt.bit.ood.solid.homework.Stats;

import ru.sbt.bit.ood.solid.homework.Containers.SalaryInfoEntry;

/**
 * Created by artem on 23.03.15.
 */
public class SalaryStatsTotal implements SalaryStats {
    private int numberOfEntries;
    private double total;

    public SalaryStatsTotal() {
        numberOfEntries = 0;
        total = 0;
    }

    @Override
    public void addEntryForStats(SalaryInfoEntry salaryInfoEntry) {
        total += salaryInfoEntry.getSalary();
        numberOfEntries++;
    }

    @Override
    public double calculateStats() {
        return total/numberOfEntries;
    }
}
