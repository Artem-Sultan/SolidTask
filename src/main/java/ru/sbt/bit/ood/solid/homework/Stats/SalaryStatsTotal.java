package ru.sbt.bit.ood.solid.homework.stats;

import ru.sbt.bit.ood.solid.homework.SalaryData;

/**
 * Created by artem on 23.03.15.
 */
public class SalaryStatsTotal implements SalaryStats {
    private double total;

    public SalaryStatsTotal() {
        total = 0;
    }

    @Override
    public void addEntryForStats(SalaryData salaryData) {
        total += salaryData.getSalary();
    }

    @Override
    public double calculateStats() {
        return total;
    }
}
