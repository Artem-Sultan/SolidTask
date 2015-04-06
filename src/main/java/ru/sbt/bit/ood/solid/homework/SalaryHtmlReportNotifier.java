package ru.sbt.bit.ood.solid.homework;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import ru.sbt.bit.ood.solid.homework.DataRetriever.SalaryDataRetriever;
import ru.sbt.bit.ood.solid.homework.DataRetriever.SalaryDataRetrieverSQL;
import ru.sbt.bit.ood.solid.homework.ReportConfigurators.ReportConfigurator;
import ru.sbt.bit.ood.solid.homework.ReportConfigurators.ReportConfiguratorHtml;
import ru.sbt.bit.ood.solid.homework.ReportNotifiers.ReportPublisher;
import ru.sbt.bit.ood.solid.homework.ReportNotifiers.ReportPublisherViaMail;
import ru.sbt.bit.ood.solid.homework.Stats.SalaryStatsTotal;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryHtmlReportNotifier {

    private Connection connection;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.connection = databaseConnection;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        //genuineMethod(departmentId, dateFrom, dateTo, recipients);

        myMetgod(departmentId, dateFrom, dateTo, recipients);
    }


    private void myMetgod(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        SalaryDataRetriever salaryDataRetriever = new SalaryDataRetrieverSQL(connection);
        ReportConfigurator reportConfigurator = new ReportConfiguratorHtml(new SalaryStatsTotal());
        ReportPublisher reportPublisher = new ReportPublisherViaMail(recipients);

        NotificationSystem notificationSystem = new NotificationSystem(salaryDataRetriever,reportConfigurator, reportPublisher);
        notificationSystem.generateReport(departmentId,dateFrom,dateTo);
    }


    private void genuineMethod(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        try {
            // prepare statement with sql
            PreparedStatement ps = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                    "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                    " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
            // inject parameters to sql
            ps.setString(0, departmentId);
            ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
            ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));
            // execute query and get the results
            ResultSet results = ps.executeQuery();
            // create a StringBuilder holding a resulting html
            StringBuilder resultingHtml = new StringBuilder();
            resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
            double totals = 0;
            while (results.next()) {
                // process each row of query results
                resultingHtml.append("<tr>"); // add row start tag
                resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>"); // appending employee name
                resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>"); // appending employee salary for period
                resultingHtml.append("</tr>"); // add row end tag
                totals += results.getDouble("salary"); // add salary to totals
            }

            resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
            resultingHtml.append("</table></body></html>");
            // now when the report is built we need to send it to the recipients list
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            // we're going to use google mail to send this message
            mailSender.setHost("mail.google.com");
            // construct the message
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            // setting message text, last parameter 'true' says that it is HTML format
            helper.setText(resultingHtml.toString(), true);
            helper.setSubject("Monthly department salary report");
            // send the message
            mailSender.send(message);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
