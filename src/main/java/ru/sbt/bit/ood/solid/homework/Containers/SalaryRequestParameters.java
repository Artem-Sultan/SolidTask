package ru.sbt.bit.ood.solid.homework.containers;

import java.time.LocalDate;

public class SalaryRequestParameters {
    private final String departmentId;
    private final LocalDate dateFrom, dateTo;

    public SalaryRequestParameters(String departmentId, LocalDate dateFrom, LocalDate dateTo) {
        this.departmentId = departmentId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
