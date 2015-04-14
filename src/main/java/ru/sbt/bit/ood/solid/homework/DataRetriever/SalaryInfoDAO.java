package ru.sbt.bit.ood.solid.homework.dataRetriever;

/**
 * Created by artem on 23.03.15.
 */
public class SalaryInfoDAO {
    private final String employeeName;
    private final double salary;

    public SalaryInfoDAO(String employeeName, double salary) {
        this.employeeName = employeeName;
        this.salary = salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public double getSalary() {
        return salary;
    }
}
