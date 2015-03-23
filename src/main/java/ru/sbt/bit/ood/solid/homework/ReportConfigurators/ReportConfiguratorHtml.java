package ru.sbt.bit.ood.solid.homework.ReportConfigurators;

import ru.sbt.bit.ood.solid.homework.SalaryInfoEntry;
import ru.sbt.bit.ood.solid.homework.SalaryStats;

import java.util.Collection;

/**
 * Created by artem on 23.03.15.
 */
public class ReportConfiguratorHtml extends ReportConfiguratorAbstract {
    private StringBuilder resultingHtml;

    public ReportConfiguratorHtml() {}
    public ReportConfiguratorHtml(SalaryStats stats) {
        super(stats);
    }

    @Override
    public String configureReport(Collection<SalaryInfoEntry> salaryData) {
        resultingHtml = new StringBuilder();
        setTableHeader();
        setTableBody(salaryData);
        appendStats();
        setTableEnd();
        return resultingHtml.toString();
    }

    private void setTableBody(Collection<SalaryInfoEntry> salaryData) {
        for (SalaryInfoEntry entry : salaryData){
            setTableRow(entry);
            if (usesStats()) stats.addEntryForStats(entry);
        }
    }

    private void setTableRow(SalaryInfoEntry entry) {
        resultingHtml.append("<tr>"); // add row start tag
        resultingHtml.append("<td>").append(entry.getEmployeeName()).append("</td>"); // appending employee name
        resultingHtml.append("<td>").append(entry.getSalary()).append("</td>"); // appending employee salary for period
        resultingHtml.append("</tr>"); // add row end tag
    }

    private void appendStats() {
        if (usesStats()) {
            resultingHtml.append("<tr><td>Total</td><td>").append(stats.calculateStats()).append("</td></tr>");
        }
    }

    private void setTableEnd() {
        resultingHtml.append("</table></body></html>");
    }

    private void setTableHeader() {
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
    }
}

