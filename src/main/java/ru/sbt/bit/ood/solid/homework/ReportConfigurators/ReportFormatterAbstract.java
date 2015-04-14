package ru.sbt.bit.ood.solid.homework.reportConfigurators;

import ru.sbt.bit.ood.solid.homework.stats.SalaryStats;

/**
 * Created by artem on 23.03.15.
 */
public abstract class ReportFormatterAbstract implements ReportFormatter {
    protected SalaryStats stats;

    public ReportFormatterAbstract() {
        stats = null;
    }
    public ReportFormatterAbstract(SalaryStats stats) {
        this.stats = stats;
    }

    protected boolean usesStats(){return stats == null? false:true;}
}
