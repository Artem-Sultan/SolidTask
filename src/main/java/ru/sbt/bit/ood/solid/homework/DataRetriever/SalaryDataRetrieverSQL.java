package ru.sbt.bit.ood.solid.homework.dataRetriever;

import ru.sbt.bit.ood.solid.homework.SalaryData;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by artem on 23.03.15.
 */
public class SalaryDataRetrieverSQL implements SalaryDataRetriever {

    private final java.sql.Connection connection;
    private PreparedStatement queryStatement;
    private Collection<SalaryData> resultCollection = null;

    public SalaryDataRetrieverSQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<SalaryData> getSalaryData(SalaryRequestParameters salaryRequestParameters) {
        try {
            ResultSet results = retrieveDataFromSQL(salaryRequestParameters);
            formCollectionFromResults(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultCollection;
    }

    private ResultSet retrieveDataFromSQL(SalaryRequestParameters salaryRequestParameters) throws SQLException {
        queryStatement = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
        // inject parameters to sql
        queryStatement.setString(0, salaryRequestParameters.getDepartmentId());
        queryStatement.setDate(1, new Date(salaryRequestParameters.getDateFrom().toEpochDay()));
        queryStatement.setDate(2, new Date(salaryRequestParameters.getDateTo().toEpochDay()));
        return queryStatement.executeQuery();
    }

    private void formCollectionFromResults(ResultSet results) throws SQLException {
        resultCollection = new ArrayList<>();
        while (results.next()) {
            SalaryData entry = new SalaryData(results.getString("emp_name"), results.getDouble("salary"));
            resultCollection.add(entry);
        }
    }
}

