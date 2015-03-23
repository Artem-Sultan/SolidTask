package ru.sbt.bit.ood.solid.homework.ReportConfigurators;

import ru.sbt.bit.ood.solid.homework.ReportConfigurators.ReportConfigurator;
import ru.sbt.bit.ood.solid.homework.SalaryStats;

/**
 * Created by artem on 23.03.15.
 */
public abstract class ReportConfiguratorAbstract implements ReportConfigurator {
    protected SalaryStats stats;

    public ReportConfiguratorAbstract() {
        stats = null;
    }
    public ReportConfiguratorAbstract(SalaryStats stats) {
        this.stats = stats;
    }

    protected boolean usesStats(){return stats == null? false:true;}
}
