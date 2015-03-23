package ru.sbt.bit.ood.solid.homework.Containers;

/**
 * Created by artem on 23.03.15.
 */
public class SalaryInfoEntry {
    private final String employeeName;
    private final double salary;

    public SalaryInfoEntry(String employeeName, double salary) {
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
