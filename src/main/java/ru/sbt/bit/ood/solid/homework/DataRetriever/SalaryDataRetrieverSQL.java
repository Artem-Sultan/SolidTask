package ru.sbt.bit.ood.solid.homework.DataRetriever;

import ru.sbt.bit.ood.solid.homework.RequestParameters;
import ru.sbt.bit.ood.solid.homework.SalaryInfoEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by artem on 23.03.15.
 */
public class SalaryDataRetrieverSQL implements SalaryDataRetriever {

    private final java.sql.Connection connection;
    private PreparedStatement queryStatement;
    private ResultSet results;
    private Collection<SalaryInfoEntry> resultCollection = null;

    public SalaryDataRetrieverSQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<SalaryInfoEntry> getSalaryData(RequestParameters requestParameters) {
        try {
            retrieveDataFromSQL(requestParameters);
            formCollectionFromResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultCollection;
    }


    private void retrieveDataFromSQL(RequestParameters requestParameters) throws SQLException {
        queryStatement = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
        // inject parameters to sql
        queryStatement.setString(0, requestParameters.departmentId);
        queryStatement.setDate(1, new Date(requestParameters.dateFrom.toEpochDay()));
        queryStatement.setDate(2, new Date(requestParameters.dateTo.toEpochDay()));
        results = queryStatement.executeQuery();
    }

    private void formCollectionFromResults() throws SQLException {
        resultCollection = new ArrayList<>();
        while (results.next()) {
            SalaryInfoEntry entry = new SalaryInfoEntry(results.getString("emp_name"), results.getDouble("salary"));
            resultCollection.add(entry);
        }
    }
}

