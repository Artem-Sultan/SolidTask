package ru.sbt.bit.ood.solid.homework.reportConfigurators;

import ru.sbt.bit.ood.solid.homework.SalaryData;
import ru.sbt.bit.ood.solid.homework.stats.SalaryStats;

import java.util.Collection;

/**
 * Created by artem on 23.03.15.
 */
public class ReportFormatterHtml extends ReportFormatterAbstract {

    public ReportFormatterHtml() {super();}
    public ReportFormatterHtml(SalaryStats stats) {
        super(stats);
    }

    @Override
    public String configureReport(Collection<SalaryData> salaryData) {
        StringBuilder resultingHtml = new StringBuilder();
        setTableHeader(resultingHtml);
        setTableBody(salaryData, resultingHtml);
        appendStats(resultingHtml);
        setTableEnd(resultingHtml);
        return resultingHtml.toString();
    }

    private void setTableBody(Collection<SalaryData> salaryData, StringBuilder resultingHtml) {
        for (SalaryData entry : salaryData){
            setTableRow(entry,resultingHtml);
            if (usesStats()) stats.addEntryForStats(entry);
        }
    }

    private void setTableRow(SalaryData entry, StringBuilder resultingHtml) {
        resultingHtml.append("<tr>"); // add row start tag
        resultingHtml.append("<td>").append(entry.getEmployeeName()).append("</td>"); // appending employee name
        resultingHtml.append("<td>").append(entry.getSalary()).append("</td>"); // appending employee salary for period
        resultingHtml.append("</tr>"); // add row end tag
    }

    private void appendStats(StringBuilder resultingHtml) {
        if (usesStats()) {
            resultingHtml.append("<tr><td>Total</td><td>").append(stats.calculateStats()).append("</td></tr>");
        }
    }

    private void setTableEnd(StringBuilder resultingHtml) {
        resultingHtml.append("</table></body></html>");
    }

    private void setTableHeader(StringBuilder resultingHtml) {
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
    }
}

